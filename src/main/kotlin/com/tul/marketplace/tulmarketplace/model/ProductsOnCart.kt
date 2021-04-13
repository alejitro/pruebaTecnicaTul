package com.tul.marketplace.tulmarketplace.model

import com.tul.marketplace.tulmarketplace.enums.CartStatus
import java.util.*
import java.util.Collections.emptyList
import javax.persistence.*

@Entity
@Table(name = "ProductsOnCart")
data class ProductsOnCart (
    @Id
    val id : UUID,
    val cartId: UUID,
    var productId: UUID,
    var quantity: Double,
    val price: Double,

)