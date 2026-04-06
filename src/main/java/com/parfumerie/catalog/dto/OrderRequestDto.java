package com.parfumerie.catalog.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.List;

public class OrderRequestDto {

    @NotBlank(message = "Numele destinatarului este obligatoriu")
    private String shippingName;

    @NotBlank(message = "Adresa de livrare este obligatorie")
    private String shippingAddress;

    @NotBlank(message = "Orașul este obligatoriu")
    private String shippingCity;

    @NotBlank(message = "Codul poștal este obligatoriu")
    private String shippingPostalCode;

    @NotBlank(message = "Telefonul este obligatoriu")
    @Pattern(regexp = "^\\+?[0-9\\- ]{7,20}$", message = "Numărul de telefon este invalid")
    private String shippingPhone;

    @NotBlank(message = "Emailul este obligatoriu")
    @Email(message = "Email invalid")
    private String email;

    private List<CartItemDto> items;

    public String getShippingName() {
        return shippingName;
    }

    public void setShippingName(String shippingName) {
        this.shippingName = shippingName;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getShippingCity() {
        return shippingCity;
    }

    public void setShippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
    }

    public String getShippingPostalCode() {
        return shippingPostalCode;
    }

    public void setShippingPostalCode(String shippingPostalCode) {
        this.shippingPostalCode = shippingPostalCode;
    }

    public String getShippingPhone() {
        return shippingPhone;
    }

    public void setShippingPhone(String shippingPhone) {
        this.shippingPhone = shippingPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<CartItemDto> getItems() {
        return items;
    }

    public void setItems(List<CartItemDto> items) {
        this.items = items;
    }
}
