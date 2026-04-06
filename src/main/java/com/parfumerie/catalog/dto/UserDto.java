package com.parfumerie.catalog.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Set;

public class UserDto {
    private Long id;

    @NotBlank(message = "Username nu poate fi gol")
    private String username;

    @NotBlank(message = "Emailul nu poate fi gol")
    @Email(message = "Email invalid")
    private String email;

    @NotBlank(message = "Numele complet nu poate fi gol")
    private String fullName;

    @Size(min = 8, message = "Parola trebuie să aibă cel puțin 8 caractere")
    private String password;

    private Set<String> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
