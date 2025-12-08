-- STERGE tabelele daca exista si creeaza-le de la zero
DROP TABLE IF EXISTS parfumuri;
DROP TABLE IF EXISTS utilizatori;

-- Creati manual tabela utilizatori
CREATE TABLE utilizatori (
                             id_utilizator BIGINT AUTO_INCREMENT PRIMARY KEY,
                             nume VARCHAR(255) NOT NULL,
                             utilizator VARCHAR(255) UNIQUE NOT NULL,
                             parola VARCHAR(255) NOT NULL,
                             rolul VARCHAR(50) NOT NULL
);

-- Creati manual tabela parfumuri
CREATE TABLE parfumuri (
                           id_parfum BIGINT AUTO_INCREMENT PRIMARY KEY,
                           id_utilizator BIGINT,
                           nume_parfum VARCHAR(255) NOT NULL,
                           brand VARCHAR(255) NOT NULL,
                           creator VARCHAR(255),
                           anul_lansarii INT,
                           tip_parfum VARCHAR(50),
                           note_varf TEXT,
                           note_baza TEXT,
                           pret DOUBLE NOT NULL,
                           stoc INT,
                           FOREIGN KEY (id_utilizator) REFERENCES utilizatori(id_utilizator)
);