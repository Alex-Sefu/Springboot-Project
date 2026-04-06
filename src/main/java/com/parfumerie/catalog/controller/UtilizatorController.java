package com.parfumerie.catalog.controller;

import com.parfumerie.catalog.entity.Utilizator;
import com.parfumerie.catalog.service.UtilizatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UtilizatorController {

    @Autowired
    private UtilizatorService utilizatorService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Metoda GET: Afișeaza formularul de înregistrare
    @GetMapping("/inregistrare")
    public String afiseazaFormularInregistrare(Model model) {
        model.addAttribute("utilizatorNou", new Utilizator());
        return "inregistrare";
    }

    // Metoda POST: Proceseaza înregistrarea
    @PostMapping("/inregistrare")
    public String inregistreazaUtilizator(
            @ModelAttribute("utilizatorNou") Utilizator utilizatorNou,
            BindingResult result,
            @RequestParam("parolaRaw") String parolaRaw
    ) {
        if (result.hasErrors()) {
            return "inregistrare";
        }

        // 1. Seteaza Rolul implicit
        utilizatorNou.setRolul("ROLE_USER");

        // 2. Cripteaza Parola cu BCrypt
        String parolaCriptata = passwordEncoder.encode(parolaRaw);
        utilizatorNou.setParola(parolaCriptata);

        // 3. Seteaza numele (dacă a fost omis)
        if (utilizatorNou.getNume() == null || utilizatorNou.getNume().isEmpty()) {
            utilizatorNou.setNume(utilizatorNou.getUtilizator());
        }

        // 4. Salveaza utilizatorul
        utilizatorService.saveUtilizator(utilizatorNou);

        // Redirecționeaza la login (fara mesaj de așteptare)
        return "redirect:/login?registered";
    }
}