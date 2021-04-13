package com.tul.marketplace.tulmarketplace.facade.impl

import com.tul.marketplace.tulmarketplace.converter.CartConverter.toCartDTO
import com.tul.marketplace.tulmarketplace.dto.car.CartProductDTO
import com.tul.marketplace.tulmarketplace.dto.cart.CartDTO
import com.tul.marketplace.tulmarketplace.dto.cart.CartProductsDTO
import com.tul.marketplace.tulmarketplace.dto.cart.ProductToCartDTO
import com.tul.marketplace.tulmarketplace.facade.CartFacade
import com.tul.marketplace.tulmarketplace.facade.ProductFacade
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
        println("carCreated: $carCreated")
        val productToAdd = productFacade!!.getProductById(productToCartDTO.productId)
        val product = ProductsOnCart(
            UUID.randomUUID(),
            carCreated.carId,
            productToCartDTO.productId,
            productToCartDTO.quantity,
            productToAdd!!.price!!
        )
        cartService.addProductToCart(product)
        val productsInCar = cartService.listProductsOnCart(carCreated.carId)
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
                    existingCart.carId,
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
        return null
    }

    @Throws(Exception::class)
    override fun removeProductsFromCart(cartId: UUID, productId: UUID): CartDTO? {
        return null
    }

    @Throws(Exception::class)
    override fun deleteCart(cartId: UUID): ResponseEntity<String> {
        return ResponseEntity("void",HttpStatus.OK);
    }

    @Throws(Exception::class)
    override fun checkoutCart(cartId: UUID): CartDTO? {
        return null
    }
}