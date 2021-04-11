package com.tul.marketplace.tulmarketplace.service.impl

import lombok.extern.slf4j.Slf4j
import lombok.RequiredArgsConstructor
import com.tul.marketplace.tulmarketplace.service.ProductService
import com.tul.marketplace.tulmarketplace.repository.ProductRepository
import com.tul.marketplace.tulmarketplace.model.Product
import org.springframework.stereotype.Service
import java.util.*

@Slf4j
@RequiredArgsConstructor
@Service
class BasicProductService : ProductService {
    private val productRepository: ProductRepository? = null
    override fun createOrUpdateProduct(product: Product): Product? {
        return productRepository!!.save<Product>(product)
    }

    override fun listAllProducts(): List<Product?>? {
        return productRepository!!.findAll()
    }

    override fun deleteProduct(product: Product) {
        productRepository!!.delete(product)
    }

    override fun getProductById(productId: String): Optional<Product?> {
        return productRepository!!.findById(productId)
    }
}