package com.tul.marketplace.tulmarketplace.model

import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "product")
data class Product (
    @Id
    val productId: UUID? = null,
    val name: String? = null,
    val sku: String? = null,
    val description: String? = null,
    //Se usa un doble por simplicidad de desarrollo
    val price: Double? = null,
    val hasDiscount: Boolean = false
)