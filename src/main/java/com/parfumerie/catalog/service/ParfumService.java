package com.parfumerie.catalog.service;

import com.parfumerie.catalog.entity.Parfum;
import com.parfumerie.catalog.repository.ParfumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParfumService {

    private final ParfumRepository parfumRepository;

    @Autowired
    public ParfumService(ParfumRepository parfumRepository) {
        this.parfumRepository = parfumRepository;
    }

    public List<Parfum> findAllParfumuri() {
        return parfumRepository.findAll();
    }

    // Logica Save/Update (pentru Update Parțial)
    public Parfum saveParfum(Parfum parfumNou) {
        if (parfumNou.getIdParfum() == null) {
            return parfumRepository.save(parfumNou);
        }

        Parfum parfumExistent = parfumRepository.findById(parfumNou.getIdParfum()).orElse(null);
        if (parfumExistent == null) {
            return parfumRepository.save(parfumNou);
        }

        // Update Parțial (doar câmpurile completate)
        if (parfumNou.getNumeParfum() != null && !parfumNou.getNumeParfum().isEmpty()) {
            parfumExistent.setNumeParfum(parfumNou.getNumeParfum());
        }
        if (parfumNou.getBrand() != null && !parfumNou.getBrand().isEmpty()) {
            parfumExistent.setBrand(parfumNou.getBrand());
        }
        if (parfumNou.getCreator() != null && !parfumNou.getCreator().isEmpty()) {
            parfumExistent.setCreator(parfumNou.getCreator());
        }
        if (parfumNou.getTipParfum() != null && !parfumNou.getTipParfum().isEmpty()) {
            parfumExistent.setTipParfum(parfumNou.getTipParfum());
        }
        if (parfumNou.getNoteVarf() != null && !parfumNou.getNoteVarf().isEmpty()) {
            parfumExistent.setNoteVarf(parfumNou.getNoteVarf());
        }
        if (parfumNou.getNoteBaza() != null && !parfumNou.getNoteBaza().isEmpty()) {
            parfumExistent.setNoteBaza(parfumNou.getNoteBaza());
        }
        if (parfumNou.getPret() != null) {
            parfumExistent.setPret(parfumNou.getPret());
        }
        if (parfumNou.getStoc() != null) {
            parfumExistent.setStoc(parfumNou.getStoc());
        }
        if (parfumNou.getAnulLansarii() != null) {
            parfumExistent.setAnulLansarii(parfumNou.getAnulLansarii());
        }

        return parfumRepository.save(parfumExistent);
    }

    public Parfum getParfumById(Long id) {
        return parfumRepository.findById(id).orElse(null);
    }

    public void deleteParfum(Long id) {
        parfumRepository.deleteById(id);
    }


    // Metoda de filtrare dinamica (LIKE pentru text, EQUAL pentru select)
    public List<Parfum> filtreazaParfumuri(String brand, String creator, String tipParfum) {

        Specification<Parfum> finalSpec = null;

        // 1. BRAND (LIKE / Text Parțial)
        if (brand != null && !brand.isEmpty()) {
            String brandWildcard = "%" + brand.toLowerCase() + "%";
            Specification<Parfum> brandSpec = (root, query, builder) ->
                    builder.like(builder.lower(root.get("brand")), brandWildcard);

            finalSpec = (finalSpec == null) ? brandSpec : finalSpec.and(brandSpec);
        }

        // 2. CREATOR (LIKE / Text Parțial)
        if (creator != null && !creator.isEmpty()) {
            String creatorWildcard = "%" + creator.toLowerCase() + "%";
            Specification<Parfum> creatorSpec = (root, query, builder) ->
                    builder.like(builder.lower(root.get("creator")), creatorWildcard);

            finalSpec = (finalSpec == null) ? creatorSpec : finalSpec.and(creatorSpec);
        }

        // 3. TIP PARFUM (EQUAL / Select Dropdown)
        if (tipParfum != null && !tipParfum.isEmpty()) {
            String exactMatch = tipParfum.toLowerCase();
            Specification<Parfum> tipSpec = (root, query, builder) ->
                    builder.equal(builder.lower(root.get("tipParfum")), exactMatch);

            finalSpec = (finalSpec == null) ? tipSpec : finalSpec.and(tipSpec);
        }

        if (finalSpec == null) {
            return parfumRepository.findAll();
        }

        return parfumRepository.findAll(finalSpec);
    }

    public List<Parfum> getAllParfumes() {
        return parfumRepository.findAll();
    }
}