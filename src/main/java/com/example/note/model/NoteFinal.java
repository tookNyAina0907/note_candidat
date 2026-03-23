package com.example.note.model;

public class NoteFinal {
    private Matiere matiere;
    private double note;
    public Matiere getMatiere() {
        return matiere;
    }
    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }
    public double getNote() {
        return note;
    }
    public void setNote(double note) {
        this.note = note;
    }
    public NoteFinal(Matiere matiere, double note) {
        this.matiere = matiere;
        this.note = note;
    }
    public NoteFinal() {
    }
    
}
