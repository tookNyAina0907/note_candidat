package com.example.forage.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.forage.DAO.DemandeStatutDAO;
import com.example.forage.model.*;

@Service
public class DemandeStatutService {

    @Autowired
    private DemandeStatutDAO demandeStatutDAO;

    @Autowired
    public HeureTravailService heureTravailService;

    public void saveDemandeStatut(DemandeStatut demandeStatut) {
        Demande demande = demandeStatut.getDemande();
        if (demandeStatut.getId() == null) {
            System.out.println("update demande statut");
            if (demande.getDemandeStatuts() != null && !demande.getDemandeStatuts().isEmpty()) {
                DemandeStatut previousStatut = demande.getDemandeStatuts().get(0);
                Duration d = getDurationEntreDemandeStatut_(previousStatut.getDateStatut(),
                        demandeStatut.getDateStatut());
                previousStatut.setDuree(d.toHours());
                Duration dMethode = getDurationEntreDemandeStatut(previousStatut.getDateStatut(),
                        demandeStatut.getDateStatut());
                System.out.println("heureeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" + previousStatut.getDateStatut() + "  "
                        + demandeStatut.getDateStatut());
                previousStatut.setDureeMethode(dMethode.toHours());
                demandeStatutDAO.save(previousStatut);

            }
            demandeStatutDAO.save(demandeStatut);

        } else {
            if (demande.getDemandeStatuts() != null && !demande.getDemandeStatuts().isEmpty()) {
                setUpadateDemandeStatut(demande.getDemandeStatuts(), demandeStatut);
                for (int i = 0; i < demande.getDemandeStatuts().size(); i++) {
                    if (i + 1 < demande.getDemandeStatuts().size()) {
                        DemandeStatut ds1 = demande.getDemandeStatuts().get(i);
                        DemandeStatut ds2 = demande.getDemandeStatuts().get(i + 1);
                        Duration d = getDurationEntreDemandeStatut_(ds2.getDateStatut(), ds1.getDateStatut());
                        ds1.setDuree(d.toHours());
                        Duration dMethode = getDurationEntreDemandeStatut(ds2.getDateStatut(), ds1.getDateStatut());
                        ds1.setDureeMethode(dMethode.toHours());
                        demandeStatutDAO.save(ds1);
                        System.out.println("Updateheureeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" + ds2.getDateStatut() + "  "
                                + ds1.getDateStatut());
                        System.out
                                .println("ddddddddddddddddddddddddddddddddd-------------------" + d + "  " + dMethode);
                    }
                }
                demandeStatutDAO.save(getDemandeStatutById(demande.getDemandeStatuts(), demandeStatut.getId()));
            }
        }
    }

    public List<DemandeStatut> getFilteredStatuts(Long demandeId, LocalDateTime start, LocalDateTime end) {
        if (start != null && end != null) {
            return demandeStatutDAO.findByDemandeIdAndDateStatutBetween(demandeId, start, end);
        }
        return null;
    }

    public DemandeStatut findById(Long id) {
        return demandeStatutDAO.findById(id).orElse(null);
    }

    public Long countByStatutId(Long statutId) {
        return demandeStatutDAO.countByStatutId(statutId);
    }

    public List<DemandeStatut> findByStatutId(Long statutId) {
        return demandeStatutDAO.findByStatutId(statutId);
    }

    public Duration getDurationEntreDemandeStatut(LocalDateTime demandeStatut1, LocalDateTime demandeStatut2) {
        Duration d = Duration.between(demandeStatut1, demandeStatut2).abs();
        return d.abs();
    }

    public Duration getDurationEntreDemandeStatut_(LocalDateTime demandeStatut1, LocalDateTime demandeStatut2) {
        Duration d = Duration.between(demandeStatut1, demandeStatut2).abs();
        HeureTravail heureTravail = heureTravailService.getHeureTravail();
        Long dureeTravail = Long.valueOf((long) heureTravail.getFin().getHour() - heureTravail.getDebut().getHour());
        Long heures = dureeTravail * (d.toDays());
        Long heures1 = 0L;
        Long heures2 = 0L;

        if (isInWorkingHours(demandeStatut2)) {
            heures2 = Long.valueOf((long) heureTravail.getFin().getHour() - demandeStatut2.getHour());
        }
        if (isInWorkingHours(demandeStatut1)) {
            heures1 = Long.valueOf((long) demandeStatut1.getHour() - heureTravail.getDebut().getHour());
        }
        if (d.toHours() > 0) {
            d = Duration.ofHours(heures + heures1 + heures2);
        }
        // d = Duration.ofHours(heures + heures1 + heures2);
        return d.abs();
    }

    public boolean isWeekend(LocalDateTime date) {
        return date.getDayOfWeek().getValue() >= 6;
    }

    public boolean isInWorkingHours(LocalDateTime date) {
        HeureTravail heureTravail = heureTravailService.getHeureTravail();
        int startHour = heureTravail.getDebut().getHour();
        int endHour = heureTravail.getFin().getHour();
        int currentHour = date.getHour();
        return currentHour >= startHour && currentHour < endHour;
    }

    public void MiseAjourDuree(Demande demande) {
        DemandeStatut demandeStatut1 = demande.getDemandeStatuts().get(0);
        Duration d = getDurationEntreDemandeStatut_(demandeStatut1.getDateStatut(), LocalDateTime.now());
        upadateDemandeStatutDuration(demandeStatut1.getId(), d);
    }

    private void upadateDemandeStatutDuration(Long id, Duration d) {
        DemandeStatut demandeStatut = findById(id);
        if (demandeStatut != null) {
            demandeStatut.setDuree(d.toHours());
            saveDemandeStatut(demandeStatut);
        }
    }

    private void sortByDate(List<DemandeStatut> statuts) {
        if (statuts != null) {
            statuts.sort((s1, s2) -> s1.getDateStatut().compareTo(s2.getDateStatut()));
        }
    }

    private void setUpadateDemandeStatut(List<DemandeStatut> statuts, DemandeStatut demandeStatut) {
        if (statuts != null) {
            for (DemandeStatut ds : statuts) {
                if (ds.getId().equals(demandeStatut.getId())) {
                    ds.setDateStatut(demandeStatut.getDateStatut());
                    ds.setObservation(demandeStatut.getObservation());
                    ds.setDuree(demandeStatut.getDuree());
                    break;
                }
            }
            sortByDate(statuts);
        }

    }

    private DemandeStatut getDemandeStatutById(List<DemandeStatut> statuts, Long id) {
        if (statuts != null) {
            for (DemandeStatut ds : statuts) {
                if (ds.getId().equals(id)) {
                    return ds;
                }
            }
        }
        return null;
    }

}
