package com.parfumerie.catalog.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull
    private OrderStatus status = OrderStatus.PENDING;

    @Column(name = "total_amount", nullable = false)
    @DecimalMin(value = "0.0", inclusive = false, message = "Totalul comenzii trebuie să fie pozitiv")
    private Double totalAmount;

    @Column(name = "shipping_name", nullable = false)
    @NotBlank(message = "Numele destinatarului este obligatoriu")
    private String shippingName;

    @Column(name = "shipping_address", nullable = false)
    @NotBlank(message = "Adresa de livrare este obligatorie")
    private String shippingAddress;

    @Column(name = "shipping_city", nullable = false)
    @NotBlank(message = "Orașul este obligatoriu")
    private String shippingCity;

    @Column(name = "shipping_postal_code", nullable = false)
    @NotBlank(message = "Codul poștal este obligatoriu")
    private String shippingPostalCode;

    @Column(name = "shipping_phone", nullable = false)
    @NotBlank(message = "Numărul de telefon este obligatoriu")
    private String shippingPhone;

    @Column(name = "payment_intent_id")
    private String paymentIntentId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
