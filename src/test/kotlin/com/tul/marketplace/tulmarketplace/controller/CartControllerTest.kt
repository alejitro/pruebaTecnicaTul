package com.tul.marketplace.tulmarketplace.controller

import com.tul.marketplace.tulmarketplace.facade.CartFacade
import com.tul.marketplace.tulmarketplace.service.ProductService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired

internal class CartControllerTest {

    @Autowired
    val cartFacade: CartFacade? = null
    @Autowired
    val productService : ProductService? = null

    @BeforeEach
    fun setUp() {

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