package com.parfumerie.catalog.dto;

public class ParfumDTO {

    private Long idParfum;
    private Long idUtilizator;
    private String numeParfum;
    private String brand;
    private String creator;
    private Integer anulLansarii;
    private String tipParfum;
    private String noteVarf;
    private String noteBaza;
    private Double pret;
    private Integer stoc;

    public Long getIdParfum() {
        return idParfum;
    }

    public void setIdParfum(Long idParfum) {
        this.idParfum = idParfum;
    }

    public Long getIdUtilizator() {
        return idUtilizator;
    }

    public void setIdUtilizator(Long idUtilizator) {
        this.idUtilizator = idUtilizator;
    }

    public String getNumeParfum() {
        return numeParfum;
    }

    public void setNumeParfum(String numeParfum) {
        this.numeParfum = numeParfum;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Integer getAnulLansarii() {
        return anulLansarii;
    }

    public void setAnulLansarii(Integer anulLansarii) {
        this.anulLansarii = anulLansarii;
    }

    public String getTipParfum() {
        return tipParfum;
    }

    public void setTipParfum(String tipParfum) {
        this.tipParfum = tipParfum;
    }

    public String getNoteVarf() {
        return noteVarf;
    }

    public void setNoteVarf(String noteVarf) {
        this.noteVarf = noteVarf;
    }

    public String getNoteBaza() {
        return noteBaza;
    }

    public void setNoteBaza(String noteBaza) {
        this.noteBaza = noteBaza;
    }

    public Double getPret() {
        return pret;
    }

    public void setPret(Double pret) {
        this.pret = pret;
    }

    public Integer getStoc() {
        return stoc;
    }

    public void setStoc(Integer stoc) {
        this.stoc = stoc;
    }
}

