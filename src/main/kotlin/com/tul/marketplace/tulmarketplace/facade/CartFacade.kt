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
    fun listCartProducts(carId: UUID) : CartProductsDTO?

    @Throws(Exception::class)
    fun addProductToCart(carId: UUID, productToCartDTO: ProductToCartDTO): CartDTO?

    @Throws(Exception::class)
    fun editProductInCart(carId: UUID, productId: UUID, quantity: Double): CartDTO?

    @Throws(Exception::class)
    fun removeProductsFromCart(carId: UUID, productId: UUID): CartDTO?

    @Throws(Exception::class)
    fun deleteCart(cartId: UUID) : ResponseEntity<String>

    @Throws(Exception::class)
    fun checkoutCart(carId: UUID) : CartDTO?
}