package com.tul.marketplace.tulmarketplace.controller


import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import com.tul.marketplace.tulmarketplace.facade.ProductFacade
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import com.tul.marketplace.tulmarketplace.dto.product.CreateOrEditProductDTO
import com.tul.marketplace.tulmarketplace.dto.product.ProductDTO
import org.springframework.web.bind.annotation.PutMapping
import kotlin.Throws
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.DeleteMapping
import java.lang.Exception

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/product")
class ProductController {
    private val productFacade: ProductFacade? = null
    @PostMapping(path = ["/"])
    fun createProduct(@RequestBody createProductDTO: CreateOrEditProductDTO?): ProductDTO? {
        return productFacade!!.createProduct(createProductDTO)
    }

    @PutMapping(path = ["/{productId}/"])
    @Throws(Exception::class)
    fun updateProduct(
        @PathVariable productId: String?,
        @RequestBody updateProductDTO: CreateOrEditProductDTO?
    ): ProductDTO? {
        return productFacade!!.updateProduct(productId!!, updateProductDTO)
    }

    @GetMapping(path = ["/"])
    fun listProducts(): List<ProductDTO?>? {
        return productFacade!!.listAllProducts()
    }

    @DeleteMapping(path = ["/{productId}/"])
    @Throws(Exception::class)
    fun rejectProductOffer(@PathVariable productId: String?) {
        productFacade!!.deleteProduct(productId)
    }
}