package com.example.forage.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.forage.model.HeureTravail;

@Repository
public interface HeureTravailDAO extends JpaRepository<HeureTravail, Long> {
    
}
