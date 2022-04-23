package com.example.tp.repository;

import com.example.tp.entites.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedcinRepository extends JpaRepository<Medecin,Long> {
    Medecin findByNom(String name);
}
