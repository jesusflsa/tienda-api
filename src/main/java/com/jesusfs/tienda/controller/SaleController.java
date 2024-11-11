package com.jesusfs.tienda.controller;

import com.jesusfs.tienda.dto.sale.CreateSaleDTO;
import com.jesusfs.tienda.dto.sale.ResponseSaleDTO;
import com.jesusfs.tienda.model.Sale;
import com.jesusfs.tienda.service.SaleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
@AllArgsConstructor
@PreAuthorize("denyAll()")
public class SaleController {
    private SaleService saleService;

    @PostMapping
    @PreAuthorize("hasAnyRole({'ADMIN', 'DEVELOPER', 'WORKER'})")
    public ResponseSaleDTO createSale(@RequestBody @Valid CreateSaleDTO saleDTO) {
        Sale sale = saleService.createSale(saleDTO);
        return new ResponseSaleDTO(sale);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole({'ADMIN', 'DEVELOPER', 'WORKER'})")
    public List<ResponseSaleDTO> getSales() {
        List<Sale> sales = saleService.getSales();
        return sales.stream().map(ResponseSaleDTO::new).toList();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole({'ADMIN', 'DEVELOPER', 'WORKER'})")
    public ResponseSaleDTO getSaleById(@PathVariable Long id) {
        Sale sale = saleService.getSaleById(id);
        return new ResponseSaleDTO(sale);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole({'ADMIN', 'DEVELOPER'})")
    public ResponseEntity<?> deleteSale(@PathVariable Long id) {
        saleService.deleteSale(id);
        return ResponseEntity.ok().build();
    }
}
