package com.tul.marketplace.tulmarketplace.model

import com.tul.marketplace.tulmarketplace.enums.CartStatus
import java.util.*
import java.util.Collections.emptyList
import javax.persistence.*

@Entity
@Table(name = "CAR")
data class Cart (
    @Id
    val carId: UUID = UUID.randomUUID(),
    //Se usa un doble por simplicidad de desarrollo
    val totalPrice: Double? = 0.0,
    val status: CartStatus = CartStatus.PENDING
)