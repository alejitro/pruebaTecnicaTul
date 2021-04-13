package com.tul.marketplace.tulmarketplace.dto.cart


import com.tul.marketplace.tulmarketplace.model.ProductsOnCart

data class CartProductsDTO(
    val content: List<ProductsOnCart>
)