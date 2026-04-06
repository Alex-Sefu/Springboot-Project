package com.parfumerie.catalog.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    @NotBlank(message = "Numele parfumului este obligatoriu")
    @Size(max = 200, message = "Numele parfumului nu poate depăși 200 de caractere")
    private String numeParfum;

    @Column(name = "brand", nullable = false)
    @NotBlank(message = "Brand-ul este obligatoriu")
    private String brand;

    @Column(name = "creator")
    private String creator;

    @Column(name = "anul_lansarii")
    @Min(value = 1900, message = "Anul lansării trebuie să fie după 1900")
    @Max(value = 2030, message = "Anul lansării nu poate fi în viitor")
    private Integer anulLansarii; // anul_fabricatiei

    @Column(name = "tip_parfum")
    private String tipParfum; // tipul_de_parfum (e.g., Eau de Parfum, Cologne)

    @Column(name = "note_varf")
    private String noteVarf;

    @Column(name = "note_baza")
    private String noteBaza;

    @Column(name = "pret", nullable = false)
    @NotNull(message = "Prețul este obligatoriu")
    @DecimalMin(value = "0.0", inclusive = false, message = "Prețul trebuie să fie mai mare decât 0")
    private Double pret; // pretul

    @Column(name = "stoc")
    @Min(value = 0, message = "Stocul nu poate fi negativ")
    private Integer stoc;

    @Column(name = "image_url")
    private String imageUrl;