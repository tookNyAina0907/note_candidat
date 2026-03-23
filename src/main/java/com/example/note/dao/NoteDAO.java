package com.example.note.dao;

import com.example.note.model.*;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteDAO extends JpaRepository<Note, Integer> {
    List<Note> findByCandidatAndMatiere(Candidat candidatId, Matiere matiereId);
}
