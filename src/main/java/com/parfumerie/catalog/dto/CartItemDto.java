package com.parfumerie.catalog.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class CartItemDto {

    private Long id;

    @NotNull(message = "Id-ul parfumului este obligatoriu")
    private Long parfumId;

    @Min(value = 1, message = "Cantitatea trebuie să fie cel puțin 1")
    private Integer quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParfumId() {
        return parfumId;
    }

    public void setParfumId(Long parfumId) {
        this.parfumId = parfumId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
