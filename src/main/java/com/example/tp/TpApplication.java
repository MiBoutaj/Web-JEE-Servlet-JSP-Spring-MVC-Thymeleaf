package com.example.tp;

import com.example.tp.entites.Consultation;
import com.example.tp.entites.Medecin;
import com.example.tp.entites.Patient;
import com.example.tp.repository.ConsultationRepository;
import com.example.tp.repository.PatientRepository;
import com.example.tp.security.service.SecurityService;
import com.example.tp.service.IHospitalService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class TpApplication {

    public static void main(String[] args) {
        SpringApplication.run(TpApplication.class, args);
    }
  // @Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository) {
        return args -> {

                patientRepository.save(new Patient(null, "Amine", new Date(), false, 122, null));
                patientRepository.save(new Patient(null, "Amal", new Date(), false, 222, null));
                patientRepository.save(new Patient(null, "Yassin", new Date(), true, 103, null));
                patientRepository.save(new Patient(null, "Yassmine", new Date(), false, 139, null));
                patientRepository.save(new Patient(null, "Ahmed", new Date(), true, 187, null));
                patientRepository.save(new Patient(null, "Youssef", new Date(), false, 198, null));
                patientRepository.save(new Patient(null, "Ayman", new Date(), true, 199, null));
                patientRepository.save(new Patient(null, "Amine", new Date(), true, 145, null));




        };
    }

    // @Bean
    CommandLineRunner saveUsers(SecurityService service) {
        return args -> {
            service.saveNewUser("mohamed", "1234", "1234");
            service.saveNewUser("yasmine", "1234", "1234");
            service.saveNewUser("hasan", "1234", "1234");
            service.saveNewUser("amine", "1234", "1234");
            service.saveNewRole("USER", "");
            service.saveNewRole("ADMIN", "");
            service.addRoleToUser("mohamed", "USER");
            service.addRoleToUser("yasmine", "USER");
            service.addRoleToUser("hasan", "USER");
            service.addRoleToUser("amine", "USER");
            service.addRoleToUser("amine", "ADMIN");


        };
    }






}
