package com.example.note.dao;

import com.example.note.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResolutionDAO extends JpaRepository<Resolution, Integer> {
}
