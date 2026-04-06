package com.parfumerie.catalog.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "utilizatori")
public class Utilizator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_utilizator")
    private Long idUtilizator;

    @Column(name = "utilizator", unique = true, nullable = false)
    private String utilizator;

    @Column(name = "parola", nullable = false)
    private String parola;

    @Column(name = "nume")
    private String nume;

    @Column(name = "rolul")
    private String rolul;


    // --- Constructor implicit ---
    public Utilizator() {
    }

    // --- Getteri și Setteri ---
    public Long getIdUtilizator() { return idUtilizator; }
    public void setIdUtilizator(Long idUtilizator) { this.idUtilizator = idUtilizator; }

    public String getUtilizator() { return utilizator; }
    public void setUtilizator(String utilizator) { this.utilizator = utilizator; }

    public String getParola() { return parola; }
    public void setParola(String parola) { this.parola = parola; }

    public String getNume() { return nume; }
    public void setNume(String nume) { this.nume = nume; }

    public String getRolul() { return rolul; }
    public void setRolul(String rolul) { this.rolul = rolul; }

}