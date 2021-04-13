package com.tul.marketplace.tulmarketplace.controller


import com.tul.marketplace.tulmarketplace.dto.cart.CartDTO
import com.tul.marketplace.tulmarketplace.dto.cart.CartProductsDTO
import com.tul.marketplace.tulmarketplace.dto.cart.ProductToCartDTO
import lombok.RequiredArgsConstructor
import com.tul.marketplace.tulmarketplace.facade.CartFacade
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.lang.Exception
import java.sql.DriverManager.println
import java.util.*

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/cart")
class CartController {

    @Autowired
    private val cartFacade: CartFacade? = null

    @PostMapping(path = ["/"])
    fun createCart(@RequestBody productToCartDTO: ProductToCartDTO): CartDTO? {
        println(productToCartDTO.toString())
        val createdCart = cartFacade?.createCart(productToCartDTO)
        println("CreatedCart: " + createdCart.toString())
        return createdCart
    }

    @PostMapping(path = ["{cartId}/add-product/"])
    @Throws(Exception::class)
    fun addProductToCart(
        @PathVariable cartId: UUID,
        @RequestBody productToCartDTO: ProductToCartDTO
    ): CartDTO? {
        return cartFacade?.addProductToCart(cartId,productToCartDTO);
    }

    @PostMapping(path = ["{cartId}/remove-product/{productId}"])
    @Throws(Exception::class)
    fun removeProductFromCart(
        @PathVariable cartId: UUID,
        @PathVariable productId: UUID
    ): CartDTO? {
        return cartFacade?.removeProductsFromCart(cartId, productId)
    }

    @GetMapping(path = ["/{cartId}/"])
    fun listCartProducts(
        @PathVariable cartId: UUID
    ): CartProductsDTO? {
        return cartFacade?.listCartProducts(cartId);
    }

    @DeleteMapping(path = ["/{cartId}/"])
    @Throws(Exception::class)
    fun deleteCart(@PathVariable cartId: UUID) :ResponseEntity<String> {
        return cartFacade!!.deleteCart(cartId)

    }

    @PostMapping(path = ["{cartId}/checkout/"])
    fun checkoutCart(@PathVariable cartId:UUID): CartDTO?{
        return cartFacade!!.checkoutCart(cartId);
    }

    @PutMapping(path = ["{cartId}/update-product/{productId}"])
    fun updateProductOnCart(
        @PathVariable cartId:UUID,
        @PathVariable productId: UUID,
        @RequestParam quantity: Double
    ): CartDTO?{
        return cartFacade!!.editProductInCart(cartId, productId, quantity)
    }
}