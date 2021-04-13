package com.tul.marketplace.tulmarketplace.dto.cart

import java.util.*

data class ProductToCartDTO(
    val productId: UUID,
    val quantity: Double
)