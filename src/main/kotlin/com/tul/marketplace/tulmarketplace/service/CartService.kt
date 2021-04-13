package com.tul.marketplace.tulmarketplace.service

import com.tul.marketplace.tulmarketplace.model.Cart
import com.tul.marketplace.tulmarketplace.model.ProductsOnCart
import java.util.*

interface CartService {
    fun createCart(): Cart

    fun listProductsOnCart(cartId: UUID) : List<ProductsOnCart>;
    fun deleteCart(cartId: UUID )
    fun addProductToCart(productsOnCart: ProductsOnCart)
    fun updateCartProduct(cartId: UUID, productId: UUID, quantity: Double) : List<ProductsOnCart>
    fun removeProductFromCart(cartId: UUID, productId: UUID)

}