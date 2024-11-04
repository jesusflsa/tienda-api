package com.jesusfs.tienda.model.sale;

import com.jesusfs.tienda.model.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sale_details")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SaleDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "unit_price")
    private double unitPrice;

    @Column(name = "quantity")
    private int quantity;

    @Transient
    private double subtotal;

    @Column(name = "discount")
    private double discount;

    @Column(name = "iva")
    private double iva;

    // Calculated
    @Column(name = "total")
    private double total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @Column(name = "active")
    private boolean active;

    public SaleDetail(Product product, int quantity, Sale sale) {
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = product.getPrice();
        this.discount = product.getDiscount();
        this.subtotal = this.unitPrice * (1 - this.discount / 100);
        this.iva = product.getIva();
        // Total = (Price - Discount) * Quantity * IVA
        this.total = (this.subtotal * quantity) * (1 + (this.iva / 100));
        this.sale = sale;
        this.active = true;
    }
}
