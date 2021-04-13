package com.tul.marketplace.tulmarketplace.facade

import com.tul.marketplace.tulmarketplace.dto.cart.CartDTO
import com.tul.marketplace.tulmarketplace.dto.cart.CartProductsDTO
import com.tul.marketplace.tulmarketplace.dto.cart.ProductToCartDTO
import org.springframework.http.ResponseEntity
import java.lang.Exception
import java.util.*

interface CartFacade {
    @Throws(Exception::class)
    fun createCart(productToCartDTO: ProductToCartDTO): CartDTO

    @Throws(Exception::class)
    fun listCartProducts(cartId: UUID) : CartProductsDTO?

    @Throws(Exception::class)
    fun addProductToCart(cartId: UUID, productToCartDTO: ProductToCartDTO): CartDTO?

    @Throws(Exception::class)
    fun editProductInCart(cartId: UUID, productId: UUID, quantity: Double): CartDTO?

    @Throws(Exception::class)
    fun removeProductsFromCart(cartId: UUID, productId: UUID): CartDTO?

    @Throws(Exception::class)
    fun deleteCart(cartId: UUID) : ResponseEntity<String>

    @Throws(Exception::class)
    fun checkoutCart(cartId: UUID) : CartDTO?
}