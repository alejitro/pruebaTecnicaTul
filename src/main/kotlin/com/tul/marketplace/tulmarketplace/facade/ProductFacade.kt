package com.tul.marketplace.tulmarketplace.facade

import com.tul.marketplace.tulmarketplace.dto.product.CreateOrEditProductDTO
import com.tul.marketplace.tulmarketplace.dto.product.ProductDTO
import org.springframework.http.ResponseEntity
import java.lang.Exception
import java.util.*

interface ProductFacade {
    fun createProduct(createProductDTO: CreateOrEditProductDTO): ProductDTO
    fun listAllProducts(): List<ProductDTO?>?

    @Throws(Exception::class)
    fun updateProduct(productId: UUID, editProductDTO: CreateOrEditProductDTO?): ProductDTO?

    @Throws(Exception::class)
    fun enableDiscount(productId: UUID):ProductDTO?

    @Throws(Exception::class)
    fun disableDiscount(productId: UUID):ProductDTO?

    @Throws(Exception::class)
    fun deleteProduct(productId: UUID) : ResponseEntity<String>

    @Throws(Exception::class)
    fun getProductById(productId: UUID) :ProductDTO?
}