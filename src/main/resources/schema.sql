-- Creeaza tabelele doar daca nu exista deja, astfel incat datele existente sa fie pastrate
CREATE TABLE IF NOT EXISTS utilizatori (
                             id_utilizator BIGINT AUTO_INCREMENT PRIMARY KEY,
                             nume VARCHAR(255) NOT NULL,
                             utilizator VARCHAR(255) UNIQUE NOT NULL,
                             parola VARCHAR(255) NOT NULL,
                             rolul VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS parfumuri (
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
                           image_url VARCHAR(500),
                           FOREIGN KEY (id_utilizator) REFERENCES utilizatori(id_utilizator)
);