# 🛒 Catalog Parfumuri (Proiect Web Dinamic)

Acesta este un proiect demonstrativ de tip catalog/e-commerce dezvoltat cu Spring Boot, JPA și Thymeleaf, implementând securitatea bazată pe roluri (ROLE_USER, ROLE_EDITOR) și funcționalități avansate de filtrare și stoc.

## 🚀 Funcționalități Cheie

* **CRUD** (Create, Read, Update, Delete) complet pentru entitatea `Parfum`.
* **Autentificare & Autorizare** (Spring Security) bazată pe roluri.
* **Criptarea parolei** utilizând BCrypt.
* **Filtrare Avansată**: Căutare după text parțial (LIKE) și filtrare strictă prin meniu derulant.
* **Înregistrare Utilizator** (cu rol implicit `ROLE_USER`).
* **Logică de Update Parțial** în Service Layer (evită suprascrierea câmpurilor goale la editare).
* **Design Modern** (Bootstrap 5, Teal & Gold Palette).

## ⚙️ Tehnologii Utilizate

* **Backend:** Java 17+ (sau versiunea folosită de tine), Spring Boot 3+
* **Bază de Date:** MySQL / H2 (în funcție de configurația finală)
* **Persistență:** Spring Data JPA / Hibernate
* **Securitate:** Spring Security
* **Frontend:** HTML5 / Thymeleaf / Bootstrap 5

## 💻 Cum se Rulează Local

### 1. Precondiții

* Instalare Java Development Kit (JDK 17+).
* Instalare MySQL Server.

### 2. Configurația Bazei de Date

Editează fișierul `src/main/resources/application.properties` și configurează conexiunea la baza ta de date:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/catalog?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=password_ta_mysql
spring.jpa.hibernate.ddl-auto=update # Folosit pentru a crea tabelele dacă nu există
```






### 3. Utilizatori Predefiniți
După rularea inițială (care execută data.sql), următoarele conturi sunt disponibile 
* Parola(alex) : Username(editor.gg) : ROLE_EDITOR 
* Parola(alex) : Username(user.ii) : ROLE_USER

### 4. Pornirea Aplicației
* Clonează/descarcă depozitul și deschide proiectul în IDE.

* Rulează clasa principală CatalogApplication.java.

* Accesează aplicația în browser la adresa: http://localhost:8080/login