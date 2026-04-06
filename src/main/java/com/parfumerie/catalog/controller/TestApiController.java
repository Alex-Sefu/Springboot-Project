package com.parfumerie.catalog.controller;

import com.parfumerie.catalog.dto.ParfumDTO;
import com.parfumerie.catalog.entity.Parfum;
import com.parfumerie.catalog.service.ParfumService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/parfumuri")
@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = "Parfumuri", description = "API pentru gestionarea parfumurilor")
public class TestApiController {

    private final ParfumService parfumService;

    // Injectăm service-ul prin constructor
    public TestApiController(ParfumService parfumService) {
        this.parfumService = parfumService;
    }

    // GET /api/parfumuri  -> listare parfumuri cu paginare
    @Operation(summary = "Obține lista de parfumuri cu paginare", description = "Returnează o pagină de parfumuri cu opțiuni de filtrare și sortare")
    @GetMapping
    public ResponseEntity<Page<ParfumDTO>> getParfumuri(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size,
            @RequestParam(defaultValue = "numeParfum,asc") String sort,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String creator
    ) {
        Sort sortObj = Sort.by(sort.split(",")[0]);
        if (sort.split(",").length > 1 && "desc".equalsIgnoreCase(sort.split(",")[1])) {
            sortObj = sortObj.descending();
        }

        Pageable pageable = PageRequest.of(page, size, sortObj);
        Page<Parfum> parfumPage;

        if (brand != null || creator != null) {
            parfumPage = parfumService.findByBrandAndCreator(brand, creator, pageable);
        } else {
            parfumPage = parfumService.findAll(pageable);
        }

        Page<ParfumDTO> dtoPage = parfumPage.map(this::toDto);
        return ResponseEntity.ok(dtoPage);
    }

    // GET /api/parfumuri/toate  -> listare toate parfumurile (DTO) - pentru compatibilitate
    @GetMapping("/toate")
    public ResponseEntity<List<ParfumDTO>> getToateParfumurile() {
        List<ParfumDTO> parfumuri = parfumService.getAllParfumes()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(parfumuri);
    }

    // GET /api/parfumuri/{id}  -> detalii parfum (DTO)
    @Operation(summary = "Obține detaliile unui parfum", description = "Returnează informațiile complete despre un parfum specific")
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

    // POST /api/parfumuri/{id}/imagine  -> upload imagine parfum
    @PreAuthorize("hasRole('EDITOR')")
    @PostMapping("/{id}/imagine")
    public ResponseEntity<ParfumDTO> uploadImagineParfum(
            @PathVariable Long id,
            @RequestParam("imagine") MultipartFile file
    ) {
        Parfum parfum = parfumService.getParfumById(id);
        if (parfum == null) {
            return ResponseEntity.notFound().build();
        }

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        try {
            // Creeaza directorul daca nu exista
            Path uploadDir = Paths.get("src/main/resources/static/images/parfumuri");
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }

            // Genereaza nume fisier unic
            String fileName = "parfum_" + id + "_" + System.currentTimeMillis() + ".jpg";
            Path filePath = uploadDir.resolve(fileName);

            // Salveaza fisierul
            Files.copy(file.getInputStream(), filePath);

            // Actualizeaza URL-ul imaginii in baza de date
            String imageUrl = "/images/parfumuri/" + fileName;
            parfum.setImageUrl(imageUrl);
            parfumService.saveParfum(parfum);

            return ResponseEntity.ok(toDto(parfum));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // GET /api/parfumuri/{id}/similare  -> parfumuri similare
    @GetMapping("/{id}/similare")
    public ResponseEntity<List<ParfumDTO>> getParfumuriSimilare(@PathVariable Long id) {
        List<ParfumDTO> similare = parfumService.getParfumuriSimilare(id)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(similare);
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
        dto.setImageUrl(parfum.getImageUrl());
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
        parfum.setImageUrl(dto.getImageUrl());
        return parfum;
    }
}
