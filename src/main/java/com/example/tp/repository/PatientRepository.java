package com.example.tp.repository;

import com.example.tp.entites.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    Page<Patient> findByNomContains(String kw , Pageable pageable);

    @Query(value = "SELECT * FROM patient WHERE patient.nom like :u " , nativeQuery = true)
    Page<Patient>  RechercheMultiCP(@Param("u") String u,Pageable pageable);


    Patient findByNom(String name);
}
