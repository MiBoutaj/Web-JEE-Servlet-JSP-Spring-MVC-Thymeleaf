package com.example.tp.service;

import com.example.tp.entites.Consultation;
import com.example.tp.entites.Medecin;
import com.example.tp.entites.Patient;
import com.example.tp.entites.RendezVous;
import com.example.tp.repository.ConsultationRepository;
import com.example.tp.repository.MedcinRepository;
import com.example.tp.repository.PatientRepository;
import com.example.tp.repository.RendezVousRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HospitalServiceImpl implements IHospitalService {

    public HospitalServiceImpl(PatientRepository patientRepository, MedcinRepository medcinRepository, RendezVousRepository rendezVousRepository, ConsultationRepository consultationRepository) {
        this.patientRepository = patientRepository;
        this.medcinRepository = medcinRepository;
        this.rendezVousRepository = rendezVousRepository;
        this.consultationRepository = consultationRepository;
    }

    private PatientRepository patientRepository;
    private MedcinRepository medcinRepository;
    private RendezVousRepository rendezVousRepository;
    private ConsultationRepository consultationRepository;

    @Bean
    @Override
    public List<Patient> listePatient() {
        return patientRepository.findAll();
    }

    @Bean
    @Override
    public List<Medecin> listeMedecin() {
        return medcinRepository.findAll();
    }

    @Override
    public Patient savePatient(Patient patient) {
      //UUUID depend de la date !!!!! unique
       //patient.setId(UUID.randomUUID().toString());
        return patientRepository.save(patient);
    }

    @Override
    public Medecin saveMedecin(Medecin medecin) {
        return medcinRepository.save(medecin);
    }

    @Override
    public RendezVous saveRDV(RendezVous rendezVous) {
        return rendezVousRepository.save(rendezVous);
    }

    @Override
    public Consultation saveConsultation(Consultation consultation) {
        return consultationRepository.save(consultation);
    }

    @Override
    public Patient PatientfindById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    @Override
    public Patient PatientfindByNom(String name) {
        return patientRepository.findByNom(name);
    }

    @Override
    public Medecin MedecinfindByNom(String name) {
        return medcinRepository.findByNom(name);
    }

    @Override
    public RendezVous RendezVousfindById(Long id) {
        return rendezVousRepository.findById(id).orElse(null);
    }
}
