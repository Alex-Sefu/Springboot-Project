package com.parfumerie.catalog.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "parfumuri")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Parfum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Folosim cheie primara auto-incrementata
    private Long idParfum; // id_parfum (cheie primara)

    // Cheie straina catre utilizatorul care a adaugat parfumul
    @Column(name = "id_utilizator")
    private Long idUtilizator; // id-ul utilizatorului care a adaugat parfumul

    @Column(name = "nume_parfum", nullable = false)
    private String numeParfum;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "creator")
    private String creator;

    @Column(name = "anul_lansarii")
    private Integer anulLansarii; // anul_fabricatiei

    @Column(name = "tip_parfum")
    private String tipParfum; // tipul_de_parfum (e.g., Eau de Parfum, Cologne)

    @Column(name = "note_varf")
    private String noteVarf;

    @Column(name = "note_baza")
    private String noteBaza;

    @Column(name = "pret", nullable = false)
    private Double pret; // pretul

    @Column(name = "stoc")
    private Integer stoc;

}