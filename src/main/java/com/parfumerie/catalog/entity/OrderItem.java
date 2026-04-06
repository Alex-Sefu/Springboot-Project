package com.parfumerie.catalog.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    @NotNull
    private Order order;

    @Column(name = "parfum_id", nullable = false)
    private Long parfumId;

    @Column(name = "parfum_name", nullable = false)
    @NotBlank(message = "Numele parfumului este obligatoriu")
    private String parfumName;

    @Column(name = "unit_price", nullable = false)
    @DecimalMin(value = "0.0", inclusive = false, message = "Prețul per unitate trebuie să fie pozitiv")
    private Double unitPrice;

    @Column(nullable = false)
    @Min(value = 1, message = "Cantitatea trebuie să fie cel puțin 1")
    private Integer quantity;

    @Column(name = "total_price", nullable = false)
    @DecimalMin(value = "0.0", inclusive = false, message = "Prețul total trebuie să fie pozitiv")
    private Double totalPrice;
}
