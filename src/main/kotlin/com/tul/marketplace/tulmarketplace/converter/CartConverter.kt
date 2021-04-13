package com.tul.marketplace.tulmarketplace.converter

import com.tul.marketplace.tulmarketplace.dto.car.CartProductDTO
import com.tul.marketplace.tulmarketplace.dto.cart.CartDTO
import com.tul.marketplace.tulmarketplace.dto.cart.CartProductsDTO
import com.tul.marketplace.tulmarketplace.model.Cart
import com.tul.marketplace.tulmarketplace.model.ProductsOnCart

object CartConverter {
    @JvmStatic
    fun toCartDTO(cart: Cart, products: CartProductsDTO?): CartDTO {
        return CartDTO(
            cart.cartId.toString(),
            products!!,
            cart.status,
            cart.discount,
            cart.totalPrice
        )
    }
    fun toCartProductDTO(products: ProductsOnCart): CartProductDTO {
        return CartProductDTO(
            products.productId.toString(),
            products.quantity
        )
    }
}