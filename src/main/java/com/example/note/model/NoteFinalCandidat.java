package com.example.note.model;

import java.util.*;

public class NoteFinalCandidat {
    private Candidat candidat;
    private List<NoteFinal> notesFinales;
    public Candidat getCandidat() {
        return candidat;
    }
    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }
    public List<NoteFinal> getNotesFinales() {
        return notesFinales;
    }
    public void setNotesFinales(List<NoteFinal> notesFinales) {
        this.notesFinales = notesFinales;
    }
    public NoteFinalCandidat(Candidat candidat, List<NoteFinal> notesFinales) {
        this.candidat = candidat;
        this.notesFinales = notesFinales;
    }
    public NoteFinalCandidat() {
    }
}
