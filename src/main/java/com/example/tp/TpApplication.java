package com.example.tp;

import com.example.tp.entites.Patient;
import com.example.tp.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class TpApplication {

    public static void main(String[] args) {
        SpringApplication.run(TpApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository){
        return args -> {
            for (int i = 0; i < 50; i++) {
                patientRepository.save(new Patient(null,"Amine",new Date(),false,122));
            }


        };
    }
}
