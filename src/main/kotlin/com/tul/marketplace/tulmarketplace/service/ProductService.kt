package com.tul.marketplace.tulmarketplace.service

import com.tul.marketplace.tulmarketplace.model.Product
import java.util.*

interface ProductService {
    fun createOrUpdateProduct(product: Product): Product
    fun listAllProducts(): List<Product?>?
    fun deleteProduct(product: Product)
    fun getProductById(productId: UUID): Optional<Product?>
}