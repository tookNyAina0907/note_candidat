package com.example.forage.service;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.forage.model.HeureTravail;

import java.time.*;

class DemandeStatutServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(DemandeStatutServiceTest.class);

    @Test
    void testDuration() {
        // HeureTravailService heureTravailService = new HeureTravailService();
        DemandeStatutService service = new DemandeStatutService();
        service.heureTravailService = new HeureTravailService() {
            @Override
            public HeureTravail getHeureTravail() {
                HeureTravail h = new HeureTravail();
                h.setDebut(LocalTime.of(8, 0));
                h.setFin(LocalTime.of(17, 0));
                return h;
            }
        };

        LocalDateTime d1 = LocalDateTime.of(2026, 4, 17, 13, 0);
        LocalDateTime d2 = LocalDateTime.of(2026, 4, 20, 10, 0);

        Duration result = service.getDurationEntreDemandeStatut_(d1, d2);

        logger.info("Durée en jour = {}", result.toHours());

        // assertEquals(26, result.toHours());
    }

}