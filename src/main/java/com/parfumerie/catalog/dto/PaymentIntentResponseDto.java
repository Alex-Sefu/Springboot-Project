package com.parfumerie.catalog.dto;

public class PaymentIntentResponseDto {
    private String paymentIntentId;
    private String clientSecret;

    public PaymentIntentResponseDto() {
    }

    public PaymentIntentResponseDto(String paymentIntentId, String clientSecret) {
        this.paymentIntentId = paymentIntentId;
        this.clientSecret = clientSecret;
    }

    public String getPaymentIntentId() {
        return paymentIntentId;
    }

    public void setPaymentIntentId(String paymentIntentId) {
        this.paymentIntentId = paymentIntentId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
