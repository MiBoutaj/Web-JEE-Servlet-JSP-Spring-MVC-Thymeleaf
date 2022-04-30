package com.example.tp.repository;


import com.example.tp.entites.Consultation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ConsultationRepository extends JpaRepository<Consultation,Long> {

    Page<Consultation> findByRapportContains(String u , Pageable pageable);

    @Query(value = "SELECT * FROM consultation WHERE consultation.date like :u or consultation.rapport like :u " , nativeQuery = true)
    Page<Consultation> RechercheMultiCC(@Param("u") String u, Pageable pageable);
}
