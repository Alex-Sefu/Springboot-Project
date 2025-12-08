package com.parfumerie.catalog.controller;

import com.parfumerie.catalog.entity.Parfum;
import com.parfumerie.catalog.service.ParfumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ParfumController {

    @Autowired
    private ParfumService parfumService;

    // --- 1. Afișare Catalog și Filtrare (READ) ---

    @GetMapping("/parfumuri")
    public String listaParfumuri(
            Authentication authentication,
            Model model,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String creator,
            @RequestParam(required = false) String tipParfum
    ) {

        List<Parfum> listaFiltrata = parfumService.filtreazaParfumuri(brand, creator, tipParfum);

        // Construcția Mesajului Dinamic
        List<String> criteriiAplicabile = new ArrayList<>();

        if (brand != null && !brand.isEmpty()) {
            criteriiAplicabile.add("brand: " + brand);
        }
        if (creator != null && !creator.isEmpty()) {
            criteriiAplicabile.add("creator: " + creator);
        }
        if (tipParfum != null && !tipParfum.isEmpty()) {
            criteriiAplicabile.add("tip parfum: " + tipParfum);
        }

        String mesajFiltru;
        if (criteriiAplicabile.isEmpty()) {
            mesajFiltru = "Toate parfumurile";
        } else {
            mesajFiltru = "Parfumurile cu " + String.join(" și ", criteriiAplicabile);
        }

        // Preluarea datelor de securitate
        String username = authentication.getName();
        boolean esteEditor = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_EDITOR"));

        // Populare Model
        model.addAttribute("parfumuri", listaFiltrata);
        model.addAttribute("numeUtilizator", username);
        model.addAttribute("esteEditor", esteEditor);
        model.addAttribute("mesajFiltru", mesajFiltru);

        // Pastreaza starea formularelor
        model.addAttribute("brandCurent", brand);
        model.addAttribute("creatorCurent", creator);
        model.addAttribute("tipParfumCurent", tipParfum);

        return "lista_parfumuri";
    }

    // --- 2. Gestiune CRUD ---

    @PostMapping("/parfumuri/sterge/{id}")
    @PreAuthorize("hasRole('EDITOR')")
    public String stergeParfum(@PathVariable("id") Long id) {
        parfumService.deleteParfum(id);
        return "redirect:/parfumuri";
    }

    @GetMapping("/adaugare_parfum")
    @PreAuthorize("hasRole('EDITOR')")
    public String afiseazaFormularAdaugare(Model model) {
        model.addAttribute("parfum", new Parfum());
        return "adaugare_parfum";
    }

    @PostMapping("/parfumuri/salveaza")
    @PreAuthorize("hasRole('EDITOR')")
    public String salveazaParfum(
            @ModelAttribute("parfum") Parfum parfum,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "adaugare_parfum";
        }

        if (parfum.getIdUtilizator() == null) {
            parfum.setIdUtilizator(1L);
        }

        parfumService.saveParfum(parfum);
        return "redirect:/parfumuri";
    }

    @GetMapping("/parfumuri/editeaza/{id}")
    @PreAuthorize("hasRole('EDITOR')")
    public String afiseazaFormularEditare(@PathVariable("id") Long id, Model model) {
        Parfum parfum = parfumService.getParfumById(id);
        if (parfum == null) {
            return "redirect:/parfumuri";
        }
        model.addAttribute("parfum", parfum);
        return "editeaza_parfum";
    }

    @PostMapping("/parfumuri/update")
    @PreAuthorize("hasRole('EDITOR')")
    public String actualizeazaParfum(
            @ModelAttribute("parfum") Parfum parfum,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "editeaza_parfum";
        }

        parfumService.saveParfum(parfum);

        return "redirect:/parfumuri";
    }

    @GetMapping("/parfumuri/detalii/{id}")
    public String afiseazaDetaliiParfum(@PathVariable("id") Long id, Model model) {
        Parfum parfum = parfumService.getParfumById(id);
        if (parfum == null) {
            return "redirect:/parfumuri";
        }
        model.addAttribute("parfum", parfum);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean esteEditor = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_EDITOR"));
        model.addAttribute("esteEditor", esteEditor);

        return "detalii_parfum";
    }
}