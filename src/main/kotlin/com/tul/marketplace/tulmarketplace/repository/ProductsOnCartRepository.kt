package com.tul.marketplace.tulmarketplace.repository

import org.springframework.data.jpa.repository.JpaRepository
import com.tul.marketplace.tulmarketplace.model.ProductsOnCart
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProductsOnCartRepository : JpaRepository<ProductsOnCart?, UUID?> {
    fun findAllByCartId(cartId: UUID): List<ProductsOnCart>

    fun deleteAllByCartId(cartId: UUID)
}