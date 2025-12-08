-- Reseteaza tabelele (optional, pentru testare)
DELETE FROM parfumuri;
DELETE FROM utilizatori;
ALTER TABLE utilizatori AUTO_INCREMENT = 1;

-- 1. Inserare Utilizatori (Parolele sunt deja criptate cu BCrypt)
-- Contul de Editor (ID 1)
INSERT INTO utilizatori (nume, utilizator, parola, rolul)
VALUES ('Gigi Georgescu', 'editor.gg', '$2a$10$J7CP8fmwcr8H.ftf8By5BuLTL5CkH7T8TxiDrVRYOu6a1ItKMl7eK', 'ROLE_EDITOR');

-- Contul de Utilizator (ID 2)
INSERT INTO utilizatori (id_utilizator, nume, utilizator, parola, rolul) VALUES
    (2, 'Ion Ionescu', 'user.ii', '$2a$10$J7CP8fmwcr8H.ftf8By5BuLTL5CkH7T8TxiDrVRYOu6a1ItKMl7eK', 'ROLE_USER');
INSERT INTO parfumuri (id_utilizator, nume_parfum, brand, creator, anul_lansarii, tip_parfum, note_varf, note_baza, pret, stoc)
VALUES
-- 5. Rabanne Phantom (Corectat)
(1, 'Phantom', 'Rabanne', 'Anne Flipo, Dominique Ropion', 2021, 'Parfum', 'Lavender, Lemon Zest, Amalfi Lemon', 'Vanilla, Vetiver, Patchouli', 520.00, 50),

-- 6. Asad Bourbon (Lattafa)
(1, 'Asad Bourbon', 'Lattafa', 'Quentin Bisch', 2024, 'Eau de Parfum', 'Black Pepper, Tobacco, Pineapple', 'Vanilla, Amber, Coffee, Benzoin', 150.00, 50),

-- 7. Paco Rabanne 1 Million
(1, '1 Million', 'Rabanne', 'Christophe Raynaud', 2008, 'Parfum', 'Grapefruit, Mint, Blood Orange', 'Leather, Amber, Woods, Patchouli', 510.00, 50),

-- 8. Jean Paul Gaultier Le Male Elixir
(1, 'Le Male Elixir', 'Jean Paul Gaultier', 'Quentin Bisch', 2023, 'Parfum', 'Lavender, Mint', 'Vanilla, Honey, Tonka Bean, Tobacco', 520.00, 50),

-- 9. Khamrah Qawha (Lattafa)
(1, 'Khamrah Qawha', 'Lattafa', 'Nisrine Bouazzaoui Grillié', 2023, 'Eau de Parfum', 'Cinnamon, Cardamom, Ginger', 'Praline, Vanilla, Tonka Bean, Musk', 180.00, 50),

-- 10. Armani Stronger with you Intensely
(1, 'Stronger with you Intensely', 'Armani', 'Cecile Matton', 2019, 'Eau de Parfum', 'Pink Pepper, Juniper, Violet', 'Vanilla, Amberwood, Tonka Bean', 450.00, 50),

-- 11. Jean Paul Gaultier Le Male EDT
(1, 'Le Male EDT', 'Jean Paul Gaultier', 'Francis Kurkdjian', 1995, 'Eau de Toilette', 'Mint, Lavender, Cardamom', 'Vanilla, Tonka Bean, Amber', 400.00, 50),

-- 12. YSL Myself
(1, 'Myself', 'Yves Saint Laurent', 'Christophe Raynaud', 2023, 'Eau de Parfum', 'Bergamot, Orange blossom', 'Patchouli, Ambrofix, Musk', 500.00, 50),

-- 13. Jean Paul Gaultier Le Beau Paradise Garden
(1, 'Le Beau Paradise Garden', 'Jean Paul Gaultier', 'Quentin Bisch', 2024, 'Eau de Parfum', 'Coconut water, Green fig, Mint', 'Sandalwood, Tonka Bean, Ginger', 480.00, 50),

-- 14. Erba Pura (Xerjoff)
(1, 'Erba Pura', 'Xerjoff', 'Laura Tonatto', 2019, 'Eau de Parfum', 'Sicilian Orange, Lemon, Bergamot', 'White musk, Amber, Vanilla', 950.00, 50),

-- 15. Hugo Boss Dark Blue
(1, 'Dark Blue', 'Hugo Boss', 'Laurent Bruyere', 1999, 'Eau de Toilette', 'Lime, Ginger, Orange, Grapefruit', 'Vanilla, Vetiver, Cedar, Patchouli', 280.00, 50),

-- 16. Opulent Dubai (Lattafa)
(1, 'Opulent Dubai', 'Lattafa', 'Nisrine Bouazzaoui Grillié', 2024, 'Eau de Parfum', 'Saffron, Pink pepper, Rose', 'Sandalwood, Amber, Patchouli, Oud', 160.00, 50),

-- 17. Art of Arabia 1 (Lattafa - Corectat)
(1, 'Art of Arabia 1', 'Lattafa', 'Not Specified', 2023, 'Eau de Parfum', 'Saffron, Blackcurrant, Bergamot', 'Leather, Oud, Amber, Vanilla', 140.00, 50);