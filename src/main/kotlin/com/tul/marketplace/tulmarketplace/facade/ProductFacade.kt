package com.tul.marketplace.tulmarketplace.facade

import com.tul.marketplace.tulmarketplace.dto.product.CreateOrEditProductDTO
import com.tul.marketplace.tulmarketplace.dto.product.ProductDTO
import java.lang.Exception
import kotlin.Throws

interface ProductFacade {
    fun createProduct(createProductDTO: CreateOrEditProductDTO?): ProductDTO?
    fun listAllProducts(): List<ProductDTO?>?

    @Throws(Exception::class)
    fun updateProduct(productId: String, editProductDTO: CreateOrEditProductDTO?): ProductDTO?

    @Throws(Exception::class)
    fun enableDiscount(productId: String):ProductDTO?

    @Throws(Exception::class)
    fun deleteProduct(productId: String?)
}