package com.tul.marketplace.tulmarketplace.repository

import com.tul.marketplace.tulmarketplace.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProductRepository : JpaRepository<Product, UUID> {
    /*fun findProductByName(name: String): Optional<Product?>?
    fun findProductBySku(sku: String): Optional<Product?>?*/
}