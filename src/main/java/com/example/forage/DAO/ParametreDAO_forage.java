package com.example.forage.DAO;

// import java.lang.reflect.Parameter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.forage.model.Client;
import com.example.forage.model.Parametre_forage;

@Repository
public interface ParametreDAO_forage extends JpaRepository<Parametre_forage,Long>{
    
}
