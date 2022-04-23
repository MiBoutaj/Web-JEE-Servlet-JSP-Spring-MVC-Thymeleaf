package com.example.tp.repository;


import com.example.tp.entites.RendezVous;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RendezVousRepository extends JpaRepository<RendezVous,Long> {
}


