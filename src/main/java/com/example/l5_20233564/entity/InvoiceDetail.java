package com.example.l5_20233564.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "invoice_details")
public class InvoiceDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String type;
    @NotNull
    private Integer quantity;
    @NotNull
    private Double price;
    @NotNull
    private Double subtotal;
    @ManyToOne
    @JoinColumn(name ="product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name ="invoice_id")
    private Invoice invoice;
}
