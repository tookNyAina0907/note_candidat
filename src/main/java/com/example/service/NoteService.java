package com.example.service;

import com.example.dao.NoteDAO;
import com.example.model.Candidat;
import com.example.model.Matiere;
import com.example.model.Note;
import com.example.model.Parametre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Service
public class NoteService {

    @Autowired
    private NoteDAO noteDAO;

    @Autowired
    private ParametreService parametreService;

    public List<Note> getAllNotes() {
        return noteDAO.findAll();
    }

    public Note getNoteById(Integer id) {
        return noteDAO.findById(id).orElse(null);
    }

    public Note saveNote(Note note) {
        return noteDAO.save(note);
    }

    public void deleteNoteById(Integer id) {
        noteDAO.deleteById(id);
    }

    public List<Note> getNotesByCandidatAndMatiere(Candidat candidatId, Matiere matiereId) {
        return noteDAO.findByCandidatAndMatiere(candidatId, matiereId);
    }

    public double SommeDiff(Candidat candidatId, Matiere matiereId) {
        List<Note> notes = getNotesByCandidatAndMatiere(candidatId, matiereId);
        List<Integer> idNote = new ArrayList<>();
        double sommeDiff = 0.0;
        for (int i = 0; i < notes.size(); i++) {
            idNote.add(notes.get(i).getId());
            for (int j = 0; j < notes.size(); j++) {
                if (i != j && !idNote.contains(notes.get(j).getId())) {
                    sommeDiff += Math.abs(notes.get(i).getNote() - notes.get(j).getNote());
                }
            }
        }
        return sommeDiff;
    }

    public double getMoyenneNote(List<Note> notes) {
        double calculeMoyenne = 0.0;
        double coefficientTotal = 0.0;
        for (Note note : notes) {
            calculeMoyenne += note.getNote() * note.getMatiere().getCoefficient();
            coefficientTotal += note.getMatiere().getCoefficient();
        }
        return calculeMoyenne / coefficientTotal;
    }

    public double getMaxNote(List<Note> notes) {
        double maxNote = 0.0;
        for (Note note : notes) {
            if (note.getNote() > maxNote) {
                maxNote = note.getNote();
            }
        }
        return maxNote;
    }

    public double getMinNote(List<Note> notes) {
        double minNote = Double.MAX_VALUE;
        for (Note note : notes) {
            if (note.getNote() < minNote) {
                minNote = note.getNote();
            }
        }
        return minNote;
    }

    public double CalculNoteFinal(Candidat candidatId, Matiere matiereId) {
        List<Note> notes = getNotesByCandidatAndMatiere(candidatId, matiereId);
        double noteFinal = 0.0;
        if (!notes.isEmpty()) {
            double diff = SommeDiff(candidatId, matiereId);
            List<Parametre> parametres = parametreService.getParametresByMatiere(matiereId);
            Parametre bonParametre = parametreService.getBonParametre(diff, parametres);
            if (bonParametre.getId() != null) {
                noteFinal = getBonMethodeCalcul(bonParametre, notes);
            }
        }
        return noteFinal;
    }

    public double getBonMethodeCalcul(Parametre parametre, List<Note> notes) {
        double noteFinal = 0.0;

        try {
            String nomResolution = parametre.getResolution().getNom();
            String methodName = "get" + nomResolution.substring(0, 1).toUpperCase()
                    + nomResolution.substring(1) + "Note";

            Method method = this.getClass().getMethod(methodName, List.class);
            noteFinal = (double) method.invoke(this, notes);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return noteFinal;
    }
}
