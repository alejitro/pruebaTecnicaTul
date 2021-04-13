package com.tul.marketplace.tulmarketplace.controller


import com.tul.marketplace.tulmarketplace.dto.cart.CartDTO
import com.tul.marketplace.tulmarketplace.dto.cart.CartProductsDTO
import com.tul.marketplace.tulmarketplace.dto.cart.ProductToCartDTO
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import com.tul.marketplace.tulmarketplace.facade.CartFacade
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.DeleteMapping
import java.lang.Exception
import java.util.*

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/cart")
class CartController {

    private val cartFacade: CartFacade? = null

    @PostMapping(path = ["/"])
    fun createCart(@RequestBody productToCartDTO: ProductToCartDTO): CartDTO? {
        return cartFacade?.createCart(productToCartDTO);
    }

    @PostMapping(path = ["{cartId}/add-product/"])
    @Throws(Exception::class)
    fun addProductToCart(
        @PathVariable cartId: UUID,
        @RequestBody productToCartDTO: ProductToCartDTO
    ): CartDTO? {
        return cartFacade?.addProductToCart(cartId,productToCartDTO);
    }

    @GetMapping(path = ["/{cartId}/"])
    fun listCartProducts(
        @PathVariable cartId: UUID
    ): CartProductsDTO? {
        return cartFacade?.listCartProducts(cartId);
    }
    /*
    @DeleteMapping(path = ["/{productId}/"])
    @Throws(Exception::class)
    fun deleteProduct(@PathVariable productId: UUID) :ResponseEntity<String> {
        return cartFacade!!.deleteProduct(productId)

    }

    @PostMapping(path = ["/enable-discount/{productId}/"])
    fun enableDiscount(@PathVariable productId: UUID): ProductDTO? {
        return cartFacade!!.enableDiscount(productId);
    }

    @PostMapping(path = ["/disable-discount/{productId}/"])
    fun disableDiscount(@PathVariable productId: UUID): ProductDTO?{
        return cartFacade!!.disableDiscount(productId);
    }*/
}