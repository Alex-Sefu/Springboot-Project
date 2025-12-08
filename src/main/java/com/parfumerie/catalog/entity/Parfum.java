package com.parfumerie.catalog.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "parfumuri") // Am schimbat numele tabelei din 'masini' in 'parfumuri'
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Parfum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Folosim cheie primara auto-incrementata
    private Long idParfum; // id_parfum (cheie primara)

    // Cheie străină către utilizatorul care a adăugat parfumul
    @Column(name = "id_utilizator")
    private Long idUtilizator; // id-ul utilizatorului care a adaugat parfumul [cite: 29]

    @Column(name = "nume_parfum", nullable = false)
    private String numeParfum;

    @Column(name = "brand", nullable = false)
    private String brand; // marca [cite: 29]

    @Column(name = "creator")
    private String creator;

    @Column(name = "anul_lansarii")
    private Integer anulLansarii; // anul_fabricatiei [cite: 29]

    @Column(name = "tip_parfum")
    private String tipParfum; // tipul_de_combustibil (e.g., Eau de Parfum, Cologne) [cite: 29]

    @Column(name = "note_varf")
    private String noteVarf;

    @Column(name = "note_baza")
    private String noteBaza;

    @Column(name = "pret", nullable = false)
    private Double pret; // pretul [cite: 29]

    @Column(name = "stoc")
    private Integer stoc;

    // NOTĂ: Dacă dorești să utilizezi o relație JPA (OneToMany, ManyToOne),
    // vei înlocui Long idUtilizator cu @ManyToOne(fetch = FetchType.LAZY) private Utilizator utilizator;
}