package com.tul.marketplace.tulmarketplace.facade

import com.tul.marketplace.tulmarketplace.dto.cart.ProductToCartDTO
import com.tul.marketplace.tulmarketplace.model.Product
import com.tul.marketplace.tulmarketplace.repository.ProductRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

@SpringBootTest
class CartFacadeTest {
    @Autowired
    private val cartFacade: CartFacade? = null

    @Autowired
    private val productRepository: ProductRepository? = null

    /*
    @Autowired
    val productFacade : ProductFacade? = null
    @Autowired
    val productRepository : ProductRepository? = null*/
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
    }

    @Test
    @DisplayName("Create a cart in a happy way")
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
    @DisplayName("Add product to Cart in a happy way")
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
    @DisplayName("Remove product to Cart in a happy way")
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
}