package com.example.forage.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.forage.model.TypeDevis;

@Repository
public interface TypeDevisDAO extends JpaRepository<TypeDevis, Long> {
    
}
