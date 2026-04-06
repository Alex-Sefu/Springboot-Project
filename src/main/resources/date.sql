-- Date initiale pastrate intre restarturi daca tabelul exista deja
-- utilizator: editor.gg / parola: editor123
-- utilizator: user.ii / parola: parfum123

INSERT INTO utilizatori (id_utilizator, nume, utilizator, parola, rolul)
VALUES
    (1, 'Gigi Georgescu', 'editor.gg', '$2b$12$JlYfY0X37SWsWkWY6mIAm.45jc4JxxPY.s.a.03OJdQxglAba7jKu', 'ROLE_EDITOR'),
    (2, 'Ion Ionescu', 'user.ii', '$2b$12$vb97dSPoo9mUlxqR0w1EduoUz1w9tIGDMTKBi2d774tMmxx0vVj4K', 'ROLE_USER')
ON DUPLICATE KEY UPDATE
    nume = VALUES(nume),
    parola = VALUES(parola),
    rolul = VALUES(rolul);

INSERT INTO parfumuri (id_parfum, id_utilizator, nume_parfum, brand, creator, anul_lansarii, tip_parfum, note_varf, note_baza, pret, stoc)
VALUES
    (5, 1, 'Phantom', 'Rabanne', 'Anne Flipo, Dominique Ropion', 2021, 'Parfum', 'Lavender, Lemon Zest, Amalfi Lemon', 'Vanilla, Vetiver, Patchouli', 520.00, 50),
    (6, 1, 'Asad Bourbon', 'Lattafa', 'Quentin Bisch', 2024, 'Eau de Parfum', 'Black Pepper, Tobacco, Pineapple', 'Vanilla, Amber, Coffee, Benzoin', 150.00, 50),
    (7, 1, '1 Million', 'Rabanne', 'Christophe Raynaud', 2008, 'Parfum', 'Grapefruit, Mint, Blood Orange', 'Leather, Amber, Woods, Patchouli', 510.00, 50),
    (8, 1, 'Le Male Elixir', 'Jean Paul Gaultier', 'Quentin Bisch', 2023, 'Parfum', 'Lavender, Mint', 'Vanilla, Honey, Tonka Bean, Tobacco', 520.00, 50),
    (9, 1, 'Khamrah Qawha', 'Lattafa', 'Nisrine Bouazzaoui Grillié', 2023, 'Eau de Parfum', 'Cinnamon, Cardamom, Ginger', 'Praline, Vanilla, Tonka Bean, Musk', 180.00, 50),
    (10, 1, 'Stronger with you Intensely', 'Armani', 'Cecile Matton', 2019, 'Eau de Parfum', 'Pink Pepper, Juniper, Violet', 'Vanilla, Amberwood, Tonka Bean', 450.00, 50),
    (11, 1, 'Le Male EDT', 'Jean Paul Gaultier', 'Francis Kurkdjian', 1995, 'Eau de Toilette', 'Mint, Lavender, Cardamom', 'Vanilla, Tonka Bean, Amber', 400.00, 50),
    (12, 1, 'Myself', 'Yves Saint Laurent', 'Christophe Raynaud', 2023, 'Eau de Parfum', 'Bergamot, Orange blossom', 'Patchouli, Ambrofix, Musk', 500.00, 50),
    (13, 1, 'Le Beau Paradise Garden', 'Jean Paul Gaultier', 'Quentin Bisch', 2024, 'Eau de Parfum', 'Coconut water, Green fig, Mint', 'Sandalwood, Tonka Bean, Ginger', 480.00, 50),
    (14, 1, 'Erba Pura', 'Xerjoff', 'Laura Tonatto', 2019, 'Eau de Parfum', 'Sicilian Orange, Lemon, Bergamot', 'White musk, Amber, Vanilla', 950.00, 50),
    (15, 1, 'Dark Blue', 'Hugo Boss', 'Laurent Bruyere', 1999, 'Eau de Toilette', 'Lime, Ginger, Orange, Grapefruit', 'Vanilla, Vetiver, Cedar, Patchouli', 280.00, 50),
    (16, 1, 'Opulent Dubai', 'Lattafa', 'Nisrine Bouazzaoui Grillié', 2024, 'Eau de Parfum', 'Saffron, Pink pepper, Rose', 'Sandalwood, Amber, Patchouli, Oud', 160.00, 50),
    (17, 1, 'Art of Arabia 1', 'Lattafa', 'Not Specified', 2023, 'Eau de Parfum', 'Saffron, Blackcurrant, Bergamot', 'Leather, Oud, Amber, Vanilla', 140.00, 50)
ON DUPLICATE KEY UPDATE
    id_utilizator = VALUES(id_utilizator),
    nume_parfum = VALUES(nume_parfum),
    brand = VALUES(brand),
    creator = VALUES(creator),
    anul_lansarii = VALUES(anul_lansarii),
    tip_parfum = VALUES(tip_parfum),
    note_varf = VALUES(note_varf),
    note_baza = VALUES(note_baza),
    pret = VALUES(pret),
    stoc = VALUES(stoc);