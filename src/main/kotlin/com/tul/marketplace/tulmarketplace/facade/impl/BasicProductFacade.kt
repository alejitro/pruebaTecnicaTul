package com.tul.marketplace.tulmarketplace.facade.impl

import com.tul.marketplace.tulmarketplace.converter.ProductConverter.toProductDTO
import com.tul.marketplace.tulmarketplace.dto.product.CreateOrEditProductDTO
import com.tul.marketplace.tulmarketplace.dto.product.ProductDTO
import com.tul.marketplace.tulmarketplace.facade.ProductFacade
import com.tul.marketplace.tulmarketplace.model.Product
import com.tul.marketplace.tulmarketplace.service.ProductService
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*
import java.util.function.Consumer

@RequiredArgsConstructor
@Slf4j
@Service
class BasicProductFacade : ProductFacade {
    @Autowired
    private val productService: ProductService? = null
    override fun createProduct(createProductDTO: CreateOrEditProductDTO): ProductDTO {
        //assert(createProductDTO != null)
        val productToSave = Product(
            UUID.randomUUID(),
            createProductDTO.name,
            createProductDTO.sku,
            createProductDTO.description,
            createProductDTO.price,
            createProductDTO.hasDiscount
        )
        val productCreated = productService?.createOrUpdateProduct(productToSave)!!
        return toProductDTO(productCreated)
    }

    override fun listAllProducts(): List<ProductDTO?>? {
        val products: MutableList<ProductDTO?> = ArrayList()
        val listOfProducts = productService!!.listAllProducts()!!
        listOfProducts.forEach(Consumer { product: Product? ->
            products.add(
                toProductDTO(
                    product!!
                )
            )
        })
        return products
    }

    @Throws(Exception::class)
    override fun updateProduct(productId: UUID, editProductDTO: CreateOrEditProductDTO?): ProductDTO? {
        val existingProduct = productService!!.getProductById(productId)
            .orElseThrow { Exception("Product does not exist") }!!
        val productToUpdate = Product(
            existingProduct.productId,
            editProductDTO!!.name,
            editProductDTO!!.sku,
            editProductDTO!!.description,
            editProductDTO!!.price,
            editProductDTO.hasDiscount
        )
        val productUpdated = productService.createOrUpdateProduct(productToUpdate)
        return toProductDTO(productUpdated)
    }

    @Throws(Exception::class)
    override fun deleteProduct(productId: UUID): ResponseEntity<String> {
        val productToDelete = productService!!.getProductById(productId)
            .orElseThrow { Exception("Product does not exist") }!!
        productService.deleteProduct(productToDelete)
        return ResponseEntity<String>("Product $productId deleted", HttpStatus.OK);
    }

    override fun getProductById(productId: UUID): ProductDTO? {
        val existingProduct = productService!!.getProductById(productId)
            .orElseThrow { Exception("Product does not exist") }!!
        return toProductDTO(existingProduct);
    }

    @Throws(Exception::class)
    override fun enableDiscount(productId: UUID): ProductDTO? {
        val existingProduct = productService!!.getProductById(productId = productId)
            .orElseThrow { Exception("Product does not exist") }!!
        val productToUpdate = Product(
            existingProduct.productId,
            existingProduct.name,
            existingProduct.sku,
            existingProduct.description,
            existingProduct.price,
            true
        )
        val updatedProduct = productService.createOrUpdateProduct(productToUpdate)
        return toProductDTO(updatedProduct)
    }

    @Throws(Exception::class)
    override fun disableDiscount(productId: UUID): ProductDTO? {
        val existingProduct = productService!!.getProductById(productId)
            .orElseThrow { Exception("Product does not exist") }!!
        val productToUpdate = Product(
            existingProduct.productId,
            existingProduct.name,
            existingProduct.sku,
            existingProduct.description,
            existingProduct.price,
            false
        )
        val updatedProduct = productService.createOrUpdateProduct(productToUpdate)
        return toProductDTO(updatedProduct)
    }
}