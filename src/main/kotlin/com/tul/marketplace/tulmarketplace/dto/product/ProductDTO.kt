package com.tul.marketplace.tulmarketplace.dto.product

data class ProductDTO(
    val productId: String? = null,
    val name: String? = null,
    val sku: String? = null,
    val description: String? = null,
    val price: Double? = null
)