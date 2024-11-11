package com.jesusfs.tienda.domain.sale;

import com.jesusfs.tienda.domain.client.Client;
import com.jesusfs.tienda.domain.client.ClientService;
import com.jesusfs.tienda.domain.product.Product;
import com.jesusfs.tienda.domain.product.ProductService;
import com.jesusfs.tienda.domain.sale.dto.CreateSaleDTO;
import com.jesusfs.tienda.domain.sale.dto.CreateSaleDetailDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class SaleService {
    private SaleRepository saleRepository;

    private ClientService clientService;

    private ProductService productService;

    public Sale createSale(@Valid CreateSaleDTO saleDTO) {
        // Getting client by id
        Client client = clientService.getClientById(saleDTO.clientId());
        // Creating sale
        Sale sale = new Sale(client, saleDTO.discount());
        // Saving in database to get generated id;
        sale = saleRepository.save(sale);
        // Creating details
        List<SaleDetail> saleDetailList = createSaleDetailList(saleDTO.products(), sale);
        // Adding details on the sale
        sale.setSaleDetails(saleDetailList);
        // Calculate subtotal
        sale.calculateSubtotal();
        // Saving all details on database with cascade
        return saleRepository.save(sale);
    }

    private List<SaleDetail> createSaleDetailList(List<CreateSaleDetailDTO> saleDetailDTOList, Sale sale) {
        Map<Long, Integer> saleDetailMap = new HashMap<>();
        for (CreateSaleDetailDTO detailDTO : saleDetailDTOList) {
            // Set values in variables
            Long productId = detailDTO.productId();
            int quantity = detailDTO.quantity();
            // Updating map
            saleDetailMap.put(productId, saleDetailMap.getOrDefault(productId, 0) + quantity);
        }

        List<SaleDetail> saleDetailList = new ArrayList<>();
        saleDetailMap.forEach((productId, quantity) -> {
            Product product = productService.getProductById(productId);
            productService.buy(product, quantity);
            SaleDetail saleDetail = new SaleDetail(product, quantity, sale);
            saleDetailList.add(saleDetail);
        });

        return saleDetailList;
    }

    public List<Sale> getSales() {
        return saleRepository.findByActiveTrue();
    }

    public Sale getSaleById(Long id) {
        Optional<Sale> opSale = saleRepository.findByIdAndActiveTrue(id);
        if (opSale.isEmpty()) throw new RuntimeException("This sale not exists.");

        return opSale.get();
    }

    public void deleteSale(Long id) {
        Sale sale = getSaleById(id);
        sale.setActive(false);
        saleRepository.save(sale);
    }
}
