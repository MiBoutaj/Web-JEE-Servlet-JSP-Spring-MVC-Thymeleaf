package com.example.tp.service;


import com.example.tp.entites.Consultation;
import com.example.tp.entites.Medecin;
import com.example.tp.entites.Patient;
import com.example.tp.entites.RendezVous;

import java.util.List;

public interface IHospitalService {
    List<Patient> listePatient();
    List<Medecin> listeMedecin();
    Patient savePatient(Patient patient);
    Medecin saveMedecin(Medecin medecin);
    RendezVous saveRDV(RendezVous rendezVous);
    Consultation saveConsultation(Consultation consultation);
    Patient PatientfindById(Long id);
    Patient PatientfindByNom(String name);
    Medecin MedecinfindByNom(String name);
    RendezVous RendezVousfindById(Long id);
}
