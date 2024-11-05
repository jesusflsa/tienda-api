package com.jesusfs.tienda.service;

import com.jesusfs.tienda.model.Client;
import com.jesusfs.tienda.model.Product;
import com.jesusfs.tienda.dto.sale.CreateSaleDTO;
import com.jesusfs.tienda.dto.sale.CreateSaleDetailDTO;
import com.jesusfs.tienda.model.Sale;
import com.jesusfs.tienda.model.SaleDetail;
import com.jesusfs.tienda.repository.ClientRepository;
import com.jesusfs.tienda.repository.ProductRepository;
import com.jesusfs.tienda.repository.SaleDetailRepository;
import com.jesusfs.tienda.repository.SaleRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class SaleService {
    private SaleRepository saleRepository;

    private ClientRepository clientRepository;

    private ProductRepository productRepository;

    private SaleDetailRepository saleDetailRepository;

    public Sale createSale(@Valid CreateSaleDTO saleDTO) {
        // Validations
        // Client not exists
        Optional<Client> opClient = clientRepository.findByIdAndActiveTrue(saleDTO.clientId());
        if (opClient.isEmpty()) throw new RuntimeException("Client not exists.");

        Client client = opClient.get();
        // Creating sale
        Sale sale = new Sale(client, saleDTO.discount());
        // Saving in database;
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
            // If data is not saved on the map
            if (!saleDetailMap.containsKey(productId)) {
                // Save data
                saleDetailMap.put(productId, quantity);
            } else {
                // Get value from map and add new quantity
                int savedQuantity = saleDetailMap.get(detailDTO.productId());
                saleDetailMap.replace(productId, savedQuantity + quantity);
            }
        }

        List<SaleDetail> saleDetailList = new ArrayList<>();
        saleDetailMap.keySet().forEach(productId -> {
            // Validations
            // Product not exists
            Optional<Product> opProduct = productRepository.findByIdAndActiveTrue(productId);
            if (opProduct.isEmpty()) return;

            Product product = opProduct.get();
            int quantity = saleDetailMap.get(productId);

            // Stock is less than quantity
            if (product.getStock() <= quantity) throw new RuntimeException("Cannot buy this product.");

            // Reduce stock value
            product.setStock(product.getStock() - quantity);
            productRepository.save(product);

            // Create sale detail
            SaleDetail saleDetail = new SaleDetail(product, quantity, sale);
            saleDetailList.add(saleDetail);
        });

        return saleDetailList;
    }

    public List<Sale> getSales() { return saleRepository.findByActiveTrue(); }

    public Sale getSaleById(Long id) {
        Optional<Sale> opSale = saleRepository.findByIdAndActiveTrue(id);
        if (opSale.isEmpty()) throw new RuntimeException("This sale not exists.");

        return opSale.get();
    }

    public boolean deleteSale(Long id) {
        // Validations
        // Sale not exists
        Optional<Sale> opSale = saleRepository.findByIdAndActiveTrue(id);
        if (opSale.isEmpty()) return false;

        // Getting sale
        Sale sale = opSale.get();
        sale.setActive(false);
        saleRepository.save(sale);
        return true;

    }
}
