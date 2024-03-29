package com.example.tp.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE) //jj/m/aaaa
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String rapport;
    @OneToOne
    private RendezVous rendezVous;



}
