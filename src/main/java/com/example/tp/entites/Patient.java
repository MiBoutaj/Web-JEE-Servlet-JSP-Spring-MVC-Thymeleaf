package com.example.tp.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Size(min = 4,max = 40)
    @Column(name = "Nom" , length = 250)
    private String nom;
    @Temporal(TemporalType.DATE) //jj/m/aaaa
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateNaissance;
    private boolean malade;
    @DecimalMin("100")
    private int score;
    @OneToMany(mappedBy = "patient",fetch = FetchType.EAGER)
    private Collection<RendezVous> rendezVous;

    @Override
    public String toString() {
        return nom +" " + dateNaissance +" malade=" + malade ;
    }
}
