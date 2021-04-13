package com.tul.marketplace.tulmarketplace.controller


import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import com.tul.marketplace.tulmarketplace.facade.ProductFacade
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import com.tul.marketplace.tulmarketplace.dto.product.CreateOrEditProductDTO
import com.tul.marketplace.tulmarketplace.dto.product.ProductDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.DeleteMapping
import java.lang.Exception
import java.util.*

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/product")
class ProductController {
    @Autowired
    private val productFacade: ProductFacade? = null

    @PostMapping(path = ["/"])
    fun createProduct(@RequestBody createProductDTO: CreateOrEditProductDTO): ProductDTO? {
        return productFacade?.createProduct(createProductDTO)
    }

    @PutMapping(path = ["/{productId}/"])
    @Throws(Exception::class)
    fun updateProduct(
        @PathVariable productId: UUID,
        @RequestBody updateProductDTO: CreateOrEditProductDTO?
    ): ProductDTO? {
        return productFacade!!.updateProduct(productId, updateProductDTO)
    }

    @GetMapping(path = ["/"])
    fun listProducts(): List<ProductDTO?>? {
        return productFacade!!.listAllProducts()
    }

    @DeleteMapping(path = ["/{productId}/"])
    @Throws(Exception::class)
    fun deleteProduct(@PathVariable productId: UUID) :ResponseEntity<String> {
        return productFacade!!.deleteProduct(productId)

    }

    @PostMapping(path = ["/enable-discount/{productId}/"])
    fun enableDiscount(@PathVariable productId: UUID): ProductDTO? {
        return productFacade!!.enableDiscount(productId);
    }

    @PostMapping(path = ["/disable-discount/{productId}/"])
    fun disableDiscount(@PathVariable productId: UUID): ProductDTO?{
        return productFacade!!.disableDiscount(productId);
    }
}