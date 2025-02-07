package com.melita.OrderService.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import com.melita.OrderService.validator.ValidCustomerDetails;
import com.melita.OrderService.validator.ValidOrderDetails;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ValidCustomerDetails
    private String customerName;

    @NotNull
    @ValidCustomerDetails
    private String customerEmail;

    @NotNull
    @ValidCustomerDetails
    private String customerPhone;

    @NotNull
    private String installationAddress;

    @NotNull
    private LocalDateTime installationDate;

    @NotNull
    private String timeSlot;

    @ElementCollection
    @ValidOrderDetails
    private List<String> productTypes;

    @ElementCollection
    private List<String> packageTypes;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
