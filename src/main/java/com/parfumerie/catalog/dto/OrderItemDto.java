package com.parfumerie.catalog.dto;

public class OrderItemDto {
    private Long parfumId;
    private String parfumName;
    private Integer quantity;
    private Double unitPrice;
    private Double totalPrice;

    public Long getParfumId() {
        return parfumId;
    }

    public void setParfumId(Long parfumId) {
        this.parfumId = parfumId;
    }

    public String getParfumName() {
        return parfumName;
    }

    public void setParfumName(String parfumName) {
        this.parfumName = parfumName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
