package com.tul.marketplace.tulmarketplace.facade.impl

import com.tul.marketplace.tulmarketplace.converter.CartConverter.toCartDTO
import com.tul.marketplace.tulmarketplace.dto.cart.CartDTO
import com.tul.marketplace.tulmarketplace.dto.cart.CartProductsDTO
import com.tul.marketplace.tulmarketplace.dto.cart.ProductToCartDTO
import com.tul.marketplace.tulmarketplace.enums.CartStatus
import com.tul.marketplace.tulmarketplace.facade.CartFacade
import com.tul.marketplace.tulmarketplace.facade.ProductFacade
import com.tul.marketplace.tulmarketplace.model.Cart
import com.tul.marketplace.tulmarketplace.model.ProductsOnCart
import com.tul.marketplace.tulmarketplace.service.CartService
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.sql.DriverManager.println


import java.util.*
import java.util.function.Consumer

@RequiredArgsConstructor
@Slf4j
@Service
class BasicCartFacade : CartFacade {
    @Autowired
    private val cartService: CartService? = null

    @Autowired
    private val productFacade: ProductFacade? = null
    @Throws(Exception::class)
    override fun createCart(productToCartDTO: ProductToCartDTO): CartDTO {
        val carCreated = cartService!!.createCart()
        val productToAdd = productFacade!!.getProductById(productToCartDTO.productId)
        val product = ProductsOnCart(
            UUID.randomUUID(),
            carCreated.cartId,
            productToCartDTO.productId,
            productToCartDTO.quantity,
            productToAdd!!.price!!
        )
        cartService.addProductToCart(product)
        val productsInCar = cartService.listProductsOnCart(carCreated.cartId)
        return toCartDTO(carCreated, CartProductsDTO(productsInCar))
    }

    @Throws(Exception::class)
    override fun listCartProducts(cartId: UUID): CartProductsDTO? {
        val productsInCart = cartService!!.listProductsOnCart(cartId)
        return CartProductsDTO(productsInCart)
    }

    @Throws(Exception::class)
    override fun addProductToCart(cartId: UUID, productToCartDTO: ProductToCartDTO): CartDTO? {
        val productsInCar: MutableList<ProductsOnCart> = ArrayList()
        val existingCart = cartService!!.getCart(cartId).orElseThrow { Exception("Car does not exist") }
        val productToAdd = productFacade!!.getProductById(productToCartDTO.productId)
        val existingProductsInCar = cartService.listProductsOnCart(cartId)
        existingProductsInCar.forEach { item: ProductsOnCart ->
            productsInCar.add(item)
            if (item.productId != productToCartDTO.productId) {
                val product = ProductsOnCart(
                    UUID.randomUUID(),
                    existingCart.cartId,
                    productToCartDTO.productId,
                    productToCartDTO.quantity,
                    productToAdd!!.price!!
                )
                cartService.addProductToCart(product)
            }
        }
        return toCartDTO(existingCart, CartProductsDTO(productsInCar))
    }

    @Throws(Exception::class)
    override fun editProductInCart(cartId: UUID, productId: UUID, quantity: Double): CartDTO? {
        val existingCart = cartService!!.getCart(cartId).orElseThrow { Exception("Car does not exist") }
        val existingProductsInCar = cartService.listProductsOnCart(cartId)
        existingProductsInCar.forEach { item: ProductsOnCart ->
            if (item.productId == productId) {
                cartService.updateCartProduct(cartId,item.productId,quantity)
            }
        }
        val productsUpdated = cartService.listProductsOnCart(cartId);
        return toCartDTO(existingCart, CartProductsDTO(productsUpdated))
    }

    @Throws(Exception::class)
    override fun removeProductsFromCart(cartId: UUID, productId: UUID): CartDTO? {
        val existingCart = cartService!!.getCart(cartId).orElseThrow { Exception("Car does not exist") }
        val existingProductsInCar = cartService.listProductsOnCart(cartId)
        existingProductsInCar.forEach { item: ProductsOnCart ->
            if (item.productId == productId) {
                cartService.removeProductFromCart(cartId, item.productId)
            }
        }
        val productsUpdated = cartService.listProductsOnCart(cartId);
        return toCartDTO(existingCart, CartProductsDTO(productsUpdated))
    }

    @Throws(Exception::class)
    override fun deleteCart(cartId: UUID): ResponseEntity<String> {
        val doesCartExist = cartService?.getCart(cartId)
        println(doesCartExist.toString())
        if(doesCartExist!=null){
            cartService?.deleteCart(cartId)
            val isCartDeleted= cartService!!.getCart(cartId)
            if(isCartDeleted.isEmpty){
                return ResponseEntity("Cart $cartId deleted",HttpStatus.OK);
            }
        }
        return ResponseEntity("Failed to delete the cart $cartId",HttpStatus.NO_CONTENT);
    }

    @Throws(Exception::class)
    override fun checkoutCart(cartId: UUID): CartDTO? {
        var totalPrice =0.0
        var discount = 0.0
        val existingCart = cartService!!.getCart(cartId).orElseThrow { Exception("Car does not exist") }
        val existingProductsInCar = cartService.listProductsOnCart(cartId)
        existingProductsInCar.forEach { item: ProductsOnCart ->
            var product =productFacade?.getProductById(item.productId)
            if(product?.hasDiscount == true){
                totalPrice += (product.price?.div(2)!!)*item.quantity
                discount += (product.price?.div(2)!!)*item.quantity
            }else{
                totalPrice += product?.price!!*item.quantity
            }
        }
        var cartToCheckout = Cart(
            existingCart.cartId,
            totalPrice,
            discount,
            CartStatus.COMPLETED
        )
        val cartClosed = cartService.checkoutCart(cartToCheckout)
        return toCartDTO(cartClosed, CartProductsDTO(existingProductsInCar))
    }
}