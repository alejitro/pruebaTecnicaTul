package com.tul.marketplace.tulmarketplace.dto.cart

import com.tul.marketplace.tulmarketplace.enums.CartStatus
import java.util.*

data class CartDTO(
    val cartID: UUID,
    val productsDTO: CartProductsDTO,
    val status: CartStatus,
    val discount: Double? = null,
    val totalPrice: Double? = null
)