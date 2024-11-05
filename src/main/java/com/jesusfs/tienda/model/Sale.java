package com.jesusfs.tienda.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "sales")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    private List<SaleDetail> saleDetails;

    @Transient
    private double subtotal;

    @Column(name = "sale_date")
    private Instant saleDate = Instant.now();

    @Column(name = "discount")
    private double discount;

    @Column(name = "total")
    private double total;

    @Column(name = "active")
    private boolean active;

    public Sale(Client client, double discount) {
        this.client = client;
        this.discount = discount;
        this.active = true;
    }

    public void calculateSubtotal() {
        if (saleDetails == null) return;

        this.subtotal = saleDetails.stream().map(SaleDetail::getTotal).reduce(0.0, Double::sum);
        this.total = this.subtotal * (1 - (discount / 100));
    }
}
