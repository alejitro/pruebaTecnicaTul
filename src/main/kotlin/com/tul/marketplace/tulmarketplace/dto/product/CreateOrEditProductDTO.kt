package com.tul.marketplace.tulmarketplace.dto.product

data class CreateOrEditProductDTO (
    val name: String? = null,
    val sku: String? = null,
    val description: String? = null,
    val price: Double? = null,
    val hasDiscount: Boolean = false
)