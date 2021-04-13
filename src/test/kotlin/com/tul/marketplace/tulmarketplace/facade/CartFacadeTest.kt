package com.tul.marketplace.tulmarketplace.facade;

import com.tul.marketplace.tulmarketplace.dto.cart.ProductToCartDTO;
import com.tul.marketplace.tulmarketplace.model.Product;
import com.tul.marketplace.tulmarketplace.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartFacadeTest {

    @Autowired
    private CartFacade cartFacade;
    @Autowired
    private ProductRepository productRepository;

    /*
    @Autowired
    val productFacade : ProductFacade? = null
    @Autowired
    val productRepository : ProductRepository? = null*/

    private UUID productIdA = UUID.randomUUID();
    private UUID productIdB = UUID.randomUUID();
    private UUID productIdC = UUID.randomUUID();

    @BeforeEach
    public void setUp(){
        Product productA = new Product(
                productIdA,
                "cemento Argos",
                "sku-cem-001",
                "bolsa 50 kg",
                25000.0,
                false
        );
        productRepository.save(productA);
        Product productB = new Product(
                productIdB,
                "Destornillador phillips",
                "sku-dst-001",
                "# 2",
                5000.0,
                true
        );
        productRepository.save(productB);
        Product productC = new Product(
                productIdC,
                "Varilla redonda 1/2 in",
                "sku-var-001",
                "6 mts",
                10000.0,
                false
        );
        productRepository.save(productC);
    }

    @Test
    @DisplayName("Create a cart in a happy way")
    public void createCart() throws Exception {
        ProductToCartDTO productToCart = new ProductToCartDTO(
                productIdA,
                15.0
        );
        var carCreated =cartFacade.createCart(productToCart);
        Assertions.assertAll(
                () -> Assertions.assertNotNull(carCreated.getCartID()),
                () -> Assertions.assertEquals(carCreated.getProductsDTO().getContent().get(0).getProductId(),(productIdA)),
                () -> Assertions.assertEquals(carCreated.getProductsDTO().getContent().get(0).getQuantity(),15.0)
        );
    }
}
