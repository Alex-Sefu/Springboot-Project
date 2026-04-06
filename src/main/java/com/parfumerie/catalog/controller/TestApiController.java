package com.parfumerie.catalog.controller;

import com.parfumerie.catalog.dto.ParfumDTO;
import com.parfumerie.catalog.entity.Parfum;
import com.parfumerie.catalog.service.ParfumService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/parfumuri")
@CrossOrigin(origins = "http://localhost:3000")
public class TestApiController {

    private final ParfumService parfumService;

    // Injectăm service-ul prin constructor
    public TestApiController(ParfumService parfumService) {
        this.parfumService = parfumService;
    }

    // GET /api/parfumuri  -> listare toate parfumurile (DTO)
    @GetMapping("/toate")
    public ResponseEntity<List<ParfumDTO>> getToateParfumurile() {
        List<ParfumDTO> parfumuri = parfumService.getAllParfumes()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(parfumuri);
    }

    // GET /api/parfumuri/{id}  -> detalii parfum (DTO)
    @GetMapping({"/gaseste/{id}", "/{id}"})
    public ResponseEntity<ParfumDTO> getParfumDupaId(@PathVariable Long id) {
        Parfum parfum = parfumService.getParfumById(id);
        if (parfum == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(toDto(parfum));
    }

    // POST /api/parfumuri  -> creare parfum nou (DTO in, DTO out)
    @PreAuthorize("hasRole('EDITOR')")
    @PostMapping
    public ResponseEntity<ParfumDTO> adaugaParfum(@RequestBody ParfumDTO parfumNouDto) {
        Parfum parfumNou = fromDto(parfumNouDto);
        // Asigurăm că la creare nu vine cu id setat
        parfumNou.setIdParfum(null);
        Parfum parfumSalvat = parfumService.saveParfum(parfumNou);
        return new ResponseEntity<>(toDto(parfumSalvat), HttpStatus.CREATED);
    }

    // PUT /api/parfumuri/{id}  -> actualizare completă/parțială (DTO)
    @PreAuthorize("hasRole('EDITOR')")
    @PutMapping("/{id}")
    public ResponseEntity<ParfumDTO> actualizeazaParfum(
            @PathVariable Long id,
            @RequestBody ParfumDTO parfumUpdateDto
    ) {
        Parfum parfumUpdate = fromDto(parfumUpdateDto);
        // Impunem id-ul din path peste ce vine în body
        parfumUpdate.setIdParfum(id);
        Parfum parfumActualizat = parfumService.saveParfum(parfumUpdate);

        if (parfumActualizat == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(toDto(parfumActualizat));
    }

    // DELETE /api/parfumuri/{id}  -> ștergere parfum
    @PreAuthorize("hasRole('EDITOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> stergeParfum(@PathVariable Long id) {
        Parfum existent = parfumService.getParfumById(id);
        if (existent == null) {
            return ResponseEntity.notFound().build();
        }

        parfumService.deleteParfum(id);
        return ResponseEntity.noContent().build();
    }

    // --------- Mapper intern Entity <-> DTO ---------

    private ParfumDTO toDto(Parfum parfum) {
        ParfumDTO dto = new ParfumDTO();
        dto.setIdParfum(parfum.getIdParfum());
        dto.setIdUtilizator(parfum.getIdUtilizator());
        dto.setNumeParfum(parfum.getNumeParfum());
        dto.setBrand(parfum.getBrand());
        dto.setCreator(parfum.getCreator());
        dto.setAnulLansarii(parfum.getAnulLansarii());
        dto.setTipParfum(parfum.getTipParfum());
        dto.setNoteVarf(parfum.getNoteVarf());
        dto.setNoteBaza(parfum.getNoteBaza());
        dto.setPret(parfum.getPret());
        dto.setStoc(parfum.getStoc());
        return dto;
    }

    private Parfum fromDto(ParfumDTO dto) {
        Parfum parfum = new Parfum();
        parfum.setIdParfum(dto.getIdParfum());
        parfum.setIdUtilizator(dto.getIdUtilizator());
        parfum.setNumeParfum(dto.getNumeParfum());
        parfum.setBrand(dto.getBrand());
        parfum.setCreator(dto.getCreator());
        parfum.setAnulLansarii(dto.getAnulLansarii());
        parfum.setTipParfum(dto.getTipParfum());
        parfum.setNoteVarf(dto.getNoteVarf());
        parfum.setNoteBaza(dto.getNoteBaza());
        parfum.setPret(dto.getPret());
        parfum.setStoc(dto.getStoc());
        return parfum;
    }
}
