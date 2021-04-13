package com.tul.marketplace.tulmarketplace.dto.cart

import com.tul.marketplace.tulmarketplace.enums.CartStatus

data class CartDTO(
    val cartID: String? = null,
    val productsDTO: CartProductsDTO,
    val status: CartStatus,
    val totalPrice: Double? = null
)