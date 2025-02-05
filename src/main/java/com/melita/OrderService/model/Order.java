package com.melita.OrderService.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String customerName;

    @NotNull
    private String installationAddress;

    @NotNull
    private String productType;

    @NotNull
    private String packageType;

    @NotNull
    private LocalDateTime installationDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
