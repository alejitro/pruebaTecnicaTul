package com.tul.marketplace.tulmarketplace.service.impl

import lombok.extern.slf4j.Slf4j
import lombok.RequiredArgsConstructor
import com.tul.marketplace.tulmarketplace.service.CartService
import org.springframework.beans.factory.annotation.Autowired
import com.tul.marketplace.tulmarketplace.repository.CartRepository
import com.tul.marketplace.tulmarketplace.repository.ProductsOnCartRepository
import com.tul.marketplace.tulmarketplace.model.Cart
import com.tul.marketplace.tulmarketplace.model.ProductsOnCart
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
class BasicCartService : CartService {
    @Autowired
    private val cartRepository: CartRepository? = null

    @Autowired
    private val productsOnCartRepository: ProductsOnCartRepository? = null
    override fun createCart(): Cart {
        val cartToCreate = Cart()
        return cartRepository!!.save(cartToCreate)
    }

    override fun deleteCart(cartId: UUID) {
        try {
            val existingCart = cartRepository!!.findById(cartId)
                .orElseThrow { Exception("Car does not exist") }
            cartRepository.delete(existingCart)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun updateCartProduct(cartId: UUID, productId: UUID, quantity: Double): List<ProductsOnCart> {
        val productsOnCart: MutableList<ProductsOnCart> = ArrayList()
        val products = productsOnCartRepository!!.findAll().stream().filter { (_, cartId1) -> cartId1 == cartId }
        products.forEach { item: ProductsOnCart ->
            if (item.productId == productId) {
                item.quantity = quantity
                productsOnCartRepository.save(item)
            }
            productsOnCart.add(item)
        }
        return productsOnCart
    }

    override fun removeProductFromCart(cartId: UUID, productId: UUID) {
        val products = productsOnCartRepository!!.findAll().stream().filter { (_, cartId1) -> cartId1 == cartId }
        products.forEach { item: ProductsOnCart ->
            if (item.productId != productId) {
                productsOnCartRepository.delete(item)
            }
        }
    }

    override fun listProductsOnCart(cartId: UUID): List<ProductsOnCart> {
        val productsOnCart: MutableList<ProductsOnCart> = ArrayList()
        val products = productsOnCartRepository!!.findAll().stream().filter { (_, cartId1) -> cartId1 == cartId }
        products.forEach { e: ProductsOnCart -> productsOnCart.add(e) }
        return productsOnCart
    }

    override fun addProductToCart(productsOnCart: ProductsOnCart) {
        productsOnCartRepository!!.save(productsOnCart)
    }
}