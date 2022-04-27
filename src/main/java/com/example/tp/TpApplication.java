package com.example.tp;

import com.example.tp.entites.Medecin;
import com.example.tp.entites.Patient;
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

    //   @Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository) {
        return args -> {
            for (int i = 0; i < 50; i++) {
                patientRepository.save(new Patient(null, "Amine", new Date(), false, 122, null));
            }


        };
    }

    //    @Bean
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
