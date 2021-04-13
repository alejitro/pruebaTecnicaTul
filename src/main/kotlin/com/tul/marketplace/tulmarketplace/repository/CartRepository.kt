package com.tul.marketplace.tulmarketplace.repository

import com.tul.marketplace.tulmarketplace.model.Cart
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CartRepository : JpaRepository<Cart, UUID> {

}