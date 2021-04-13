package com.tul.marketplace.tulmarketplace.converter

import com.tul.marketplace.tulmarketplace.dto.car.CartProductDTO
import com.tul.marketplace.tulmarketplace.dto.cart.CartDTO
import com.tul.marketplace.tulmarketplace.dto.cart.CartProductsDTO
import com.tul.marketplace.tulmarketplace.model.Cart
import com.tul.marketplace.tulmarketplace.model.ProductsOnCart
import com.tul.marketplace.tulmarketplace.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

object CartConverter {
    @JvmStatic
    fun toCartDTO(cart: Cart, products: CartProductsDTO?): CartDTO {
        return CartDTO(
            cart.carId.toString(),
            products!!,
            cart.status,
            cart.totalPrice
        )
    }
    fun toCartProductDTO(products: ProductsOnCart): CartProductDTO {
        return CartProductDTO(
            products.productId.toString(),
            "product.name",
            products.quantity
        )
    }
}