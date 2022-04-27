package com.example.tp.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medecin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String email;
    private String specialite;
    private String imageUrl;

    @OneToMany(mappedBy = "medecin",fetch = FetchType.EAGER)
    private Collection<RendezVous> rendezVous;

    @Override
    public String toString() {
        return nom+ " specialite= " + specialite ;
    }
}
