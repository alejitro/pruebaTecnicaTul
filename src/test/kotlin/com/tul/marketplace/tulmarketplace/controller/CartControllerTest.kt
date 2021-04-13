package com.tul.marketplace.tulmarketplace.controller

import com.tul.marketplace.tulmarketplace.dto.cart.ProductToCartDTO
import com.tul.marketplace.tulmarketplace.dto.product.CreateOrEditProductDTO
import com.tul.marketplace.tulmarketplace.facade.CartFacade
import com.tul.marketplace.tulmarketplace.facade.ProductFacade
import com.tul.marketplace.tulmarketplace.model.Product
import com.tul.marketplace.tulmarketplace.repository.ProductRepository
import com.tul.marketplace.tulmarketplace.service.ProductService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

@SpringBootTest
internal class CartControllerTest {

    @Autowired
    val cartFacade: CartFacade? = null
    @Autowired
    val productFacade : ProductFacade? = null
    @Autowired
    val productRepository : ProductRepository? = null

    val productIdA = UUID.randomUUID();
    val productIdB = UUID.randomUUID();
    val productIdC = UUID.randomUUID();
    @BeforeEach
    fun setUp() {
        val productA = Product(
            productIdA,
            name = "cemento Argos",
            sku = "sku-cem-001",
            description = "bolsa 50 kg",
            price = 25000.0,
            hasDiscount = false
        )
        productRepository?.save(productA)
        val productB = Product(
            productIdB,
            name = "Destornillador phillips",
            sku = "sku-dst-001",
            description = "# 2",
            price = 5000.0,
            hasDiscount = true
        )
        productRepository?.save(productB)
        val productC = Product(
            productIdC,
            name = "Varilla redonda 1/2 in",
            sku = "sku-var-001",
            description = "6 mts",
            price = 10000.0,
            hasDiscount = false
        )
        productRepository?.save(productC)
    }

    @Test
    fun createCart() {


    }

    @Test
    fun addProductToCart() {
    }

    @Test
    fun removeProductFromCart() {
    }

    @Test
    fun listCartProducts() {
    }

    @Test
    fun deleteCart() {
    }

    @Test
    fun checkoutCart() {
    }

    @Test
    fun updateProductOnCart() {
    }
}