package com.tul.marketplace.tulmarketplace.facade

import com.tul.marketplace.tulmarketplace.dto.cart.ProductToCartDTO
import com.tul.marketplace.tulmarketplace.enums.CartStatus
import com.tul.marketplace.tulmarketplace.model.Cart
import com.tul.marketplace.tulmarketplace.model.Product
import com.tul.marketplace.tulmarketplace.repository.CartRepository
import com.tul.marketplace.tulmarketplace.repository.ProductRepository
import com.tul.marketplace.tulmarketplace.repository.ProductsOnCartRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import java.util.*


@SpringBootTest
class CartFacadeTest {
    @Autowired
    private val cartFacade: CartFacade? = null

    @Autowired
    private val productRepository: ProductRepository? = null
    @Autowired
    private val productOnCartRepository: ProductsOnCartRepository? = null

    @Autowired
    private val cartRepository: CartRepository? = null

    private val productIdA = UUID.randomUUID()
    private val productIdB = UUID.randomUUID()
    private val productIdC = UUID.randomUUID()
    @BeforeEach
    fun setUp() {
        val productA = Product(
            productIdA,
            "cemento Argos",
            "sku-cem-001",
            "bolsa 50 kg",
            25000.0,
            false
        )
        productRepository!!.save(productA)
        val productB = Product(
            productIdB,
            "Destornillador phillips",
            "sku-dst-001",
            "# 2",
            5000.0,
            true
        )
        productRepository.save(productB)
        val productC = Product(
            productIdC,
            "Varilla redonda 1/2 in",
            "sku-var-001",
            "6 mts",
            10000.0,
            false
        )
        productRepository.save(productC)
        cartRepository!!.deleteAll()
        productOnCartRepository!!.deleteAll()
    }

    @Test
    @DisplayName("Create a cart with one product")
    @Throws(Exception::class)
    fun createCart() {
        val productToCart = ProductToCartDTO(
            productIdA,
            15.0
        )
        val (cartID, productsDTO) = cartFacade!!.createCart(productToCart)
        Assertions.assertAll(
            Executable { Assertions.assertNotNull(cartID) },
            Executable { Assertions.assertEquals(productsDTO.content[0].productId, productIdA) },
            Executable { Assertions.assertEquals(productsDTO.content[0].quantity, 15.0) }
        )
    }

    @Test
    @DisplayName("Add product to Cart")
    @Throws(Exception::class)
    fun addProductToCart() {
        val productToCart = ProductToCartDTO(
            productIdA,
            15.0
        )
        val (cartID, productsDTO) = cartFacade!!.createCart(productToCart)
        val productToCartB = ProductToCartDTO(
            productIdB,
            5.0
        )
        val cartUpdated = cartFacade.addProductToCart(cartID,productToCartB)
        Assertions.assertAll(
            Executable {
                if(cartUpdated != null){
                    Executable { Assertions.assertNotNull(cartID) }
                    Executable { Assertions.assertEquals(cartUpdated.productsDTO.content.size, 2) }
                }
            }
        )
    }

    @Test
    @DisplayName("Remove product from Cart")
    @Throws(Exception::class)
    fun removeProductFromCart() {
        val productToCart = ProductToCartDTO(
            productIdA,
            15.0
        )
        val (cartID, productsDTO) = cartFacade!!.createCart(productToCart)
        val productToCartB = ProductToCartDTO(
            productIdB,
            5.0
        )
        cartFacade.addProductToCart(cartID,productToCartB)

        val cartUpdated = cartFacade.removeProductsFromCart(cartID,productIdA)

        Assertions.assertAll(
            Executable {
                if(cartUpdated != null){
                    Executable { Assertions.assertNotNull(cartID) }
                    Executable { Assertions.assertEquals(cartUpdated.productsDTO.content.size, 1) }
                    Executable { Assertions.assertEquals(cartUpdated.productsDTO.content[0].productId, productIdB)}
                }
            }
        )
    }

    @Test
    @DisplayName("Checkout Cart")
    @Throws(Exception::class)
    fun checkoutCart() {
        val productToCart = ProductToCartDTO(
            productIdA,
            1.0
        )
        val (cartID, productsDTO) = cartFacade!!.createCart(productToCart)
        val productToCartB = ProductToCartDTO(
            productIdB,
            2.0
        )
        cartFacade.addProductToCart(cartID,productToCartB)

        val cartUpdated = cartFacade.checkoutCart(cartID)

        Assertions.assertAll(
            Executable {
                if(cartUpdated != null){
                    Executable { Assertions.assertNotNull(cartID) }
                    Executable { Assertions.assertEquals(cartUpdated.productsDTO.content.size, 2) }
                    Executable { Assertions.assertEquals(cartUpdated.totalPrice,30000.0) }
                    Executable { Assertions.assertEquals(cartUpdated.status,CartStatus.COMPLETED)}
                }
            }
        )
    }


    @Test
    @DisplayName("Delete Cart")
    @Throws(Exception::class)
    fun deleteCart() {

        val productToCart = ProductToCartDTO(
            productIdA,
            1.0
        )
        val (cartID, productsDTO) = cartFacade!!.createCart(productToCart)
        val cartDeleted = cartFacade.deleteCart(cartID)

        Assertions.assertAll(
            Executable { Assertions.assertEquals(cartDeleted.statusCode,HttpStatus.OK)}
        )
    }

    @Test
    @DisplayName("List products on Cart")
    @Throws(Exception::class)
    fun listProductsOnCart() {
        val productToCart = ProductToCartDTO(
            productIdA,
            5.0
        )
        val (cartID, productsDTO) = cartFacade!!.createCart(productToCart)
        val productToCartB = ProductToCartDTO(
            productIdB,
            10.0
        )
        cartFacade.addProductToCart(cartID,productToCartB)

        val productToCartC = ProductToCartDTO(
            productIdC,
            20.0
        )
        cartFacade.addProductToCart(cartID,productToCartC)

        val cartProducts = cartFacade.listCartProducts(cartID)

        Assertions.assertAll(
            Executable {
                if(cartProducts != null){
                    Executable { Assertions.assertEquals(cartProducts.content.size, 3) }
                    Executable { Assertions.assertEquals(cartProducts.content.iterator().next().productId, productIdA) }
                    Executable { Assertions.assertEquals(cartProducts.content.iterator().next().productId, productIdB) }
                    Executable { Assertions.assertEquals(cartProducts.content.iterator().next().productId, productIdC) }
                }
            }
        )
    }
}