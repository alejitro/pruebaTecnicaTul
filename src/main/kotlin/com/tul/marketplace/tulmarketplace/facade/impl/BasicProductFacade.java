package com.tul.marketplace.tulmarketplace.facade.impl;


import com.tul.marketplace.tulmarketplace.converter.ProductConverter;
import com.tul.marketplace.tulmarketplace.dto.product.CreateOrEditProductDTO;
import com.tul.marketplace.tulmarketplace.dto.product.ProductDTO;
import com.tul.marketplace.tulmarketplace.facade.ProductFacade;
import com.tul.marketplace.tulmarketplace.model.Product;
import com.tul.marketplace.tulmarketplace.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class BasicProductFacade implements ProductFacade {

    private final ProductService productService;
    @Override
    public ProductDTO createProduct(CreateOrEditProductDTO createProductDTO) {
        assert createProductDTO != null;
        //TODO: poner en sintaxis Kotlin
        var productToSave = new Product(
                UUID.randomUUID(),
                createProductDTO.getName(),
                createProductDTO.getSku(),
                createProductDTO.getDescription(),
                createProductDTO.getPrice(),
                createProductDTO.isHasDiscount()
        );

        var productCreated = productService.createOrUpdateProduct(productToSave);
        assert productCreated != null;
        return ProductConverter.toProductDTO(productCreated);
    }

    @Override
    public List<ProductDTO> listAllProducts() {
        List<ProductDTO> products = new ArrayList<>();
        var listOfProducts = productService.listAllProducts();
        assert listOfProducts != null;
        listOfProducts.forEach(product -> {
            products.add(ProductConverter.toProductDTO(product));
        });
        return products;
    }

    @Nullable
    @Override
    public ProductDTO updateProduct(@NotNull String productId, CreateOrEditProductDTO editProductDTO) throws Exception {
        assert editProductDTO != null;
        var existingProduct = productService.getProductById(productId)
                .orElseThrow(()->new Exception("Product does not exist"));
        //TODO: poner en sintaxis Kotlin
        var productToUpdate = new Product(
                existingProduct.getProductId(),
                editProductDTO.getName(),
                editProductDTO.getSku(),
                editProductDTO.getDescription(),
                editProductDTO.getPrice(),
                false
        );
        var productUpdated = productService.createOrUpdateProduct(productToUpdate);
        assert productUpdated != null;
        return ProductConverter.toProductDTO(productUpdated);
    }

    @Override
    public void deleteProduct(String productId) throws Exception {
        var productToDelete = productService.getProductById(productId)
                .orElseThrow(()->new Exception("Product does not exist"));
        productService.deleteProduct(productToDelete);
    }

    //@Nullable
    @Override
    public ProductDTO enableDiscount(@NotNull String Id) throws Exception {
        var productToEnableDiscount = productService.getProductById(Id)
                .orElseThrow(()->new Exception("Product does not exist"));
        Product productToUpdate = new Product(
                productToEnableDiscount.getProductId(),
                productToEnableDiscount.getName(),
                productToEnableDiscount.getSku(),
                productToEnableDiscount.getDescription(),
                productToEnableDiscount.getPrice(),
                true
        );
        var updatedProduct = productService.createOrUpdateProduct(productToUpdate);
        assert updatedProduct != null;
        return ProductConverter.toProductDTO(updatedProduct);
    }
}
