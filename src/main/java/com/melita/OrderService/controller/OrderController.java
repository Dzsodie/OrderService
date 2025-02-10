package com.melita.OrderService.controller;

import com.melita.OrderService.model.Order;
import com.melita.OrderService.service.OrderServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderServiceImpl orderServiceImpl;

    @Operation(summary = "Create a new order", description = "This endpoint allows users with ROLE_USER to place an order.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order successfully created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Order.class))),
            @ApiResponse(responseCode = "400", description = "Invalid order request",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized - user not authenticated",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden - user does not have ROLE_USER",
                    content = @Content)
    })
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    public ResponseEntity<Order> createOrder(@Valid @RequestBody Order order) {
        return ResponseEntity.ok(orderServiceImpl.placeOrder(order));
    }

    @Operation(summary = "Fetch orders from ERP", description = "Retrieves orders from an external ERP system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orders successfully retrieved",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Order.class))),
            @ApiResponse(responseCode = "500", description = "Failed to fetch orders from ERP",
                    content = @Content)
    })
    @GetMapping("/erp")
    public ResponseEntity<List<Order>> fetchOrdersFromERP() {
        List<Order> orders = orderServiceImpl.fetchOrdersFromERP();
        return ResponseEntity.ok(orders);
    }
}
