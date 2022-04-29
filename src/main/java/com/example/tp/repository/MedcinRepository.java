package com.example.tp.repository;

import com.example.tp.entites.Medecin;
import com.example.tp.entites.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MedcinRepository extends JpaRepository<Medecin,Long> {
    Medecin findByNom(String name);
    @Query(value = "SELECT * FROM medecin WHERE medecin.nom like :u or  medecin.email like :u or medecin.specialite  like :u " , nativeQuery = true)
    Page<Medecin> RechercheMultiCM(@Param("u") String u, Pageable pageable);
}
