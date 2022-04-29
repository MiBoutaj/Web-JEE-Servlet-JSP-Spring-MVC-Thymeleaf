package com.example.tp.repository;


import com.example.tp.entites.Medecin;
import com.example.tp.entites.RendezVous;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RendezVousRepository extends JpaRepository<RendezVous,Long> {

    @Query(value = "SELECT * FROM rendez_vous WHERE rendez_vous.date like :u or  rendez_vous.medecin_id like :u or rendez_vous.patient_id  like :u or rendez_vous.status  like :u " , nativeQuery = true)
    Page<RendezVous> RechercheMultiCR(@Param("u") String u, Pageable pageable);
}


