package com.tul.marketplace.tulmarketplace.facade

import com.tul.marketplace.tulmarketplace.dto.cart.ProductToCartDTO
import com.tul.marketplace.tulmarketplace.dto.product.CreateOrEditProductDTO
import com.tul.marketplace.tulmarketplace.enums.CartStatus
import com.tul.marketplace.tulmarketplace.model.Cart
import com.tul.marketplace.tulmarketplace.model.Product
import com.tul.marketplace.tulmarketplace.repository.CartRepository
import com.tul.marketplace.tulmarketplace.repository.ProductRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import java.sql.DriverManager.println
import java.util.*


@SpringBootTest
class ProductFacadeTest {
    @Autowired
    private val productFacade: ProductFacade? = null

    @Autowired
    private val productRepository: ProductRepository? = null

    @BeforeEach
    fun setUp() {
       productRepository!!.deleteAll()
    }

    @Test
    @DisplayName("Create a product")
    @Throws(Exception::class)
    fun createProduct() {
        val productToCreate = CreateOrEditProductDTO(
            "cemento Argos",
            "sku-cem-001",
            "bolsa 50 kg",
            25000.0,
            false
        )
        val product = productFacade!!.createProduct(productToCreate)
        Assertions.assertAll(
            Executable { Assertions.assertNotNull(product.productId) },
            Executable { Assertions.assertEquals(product.name,"cemento Argos") },
            Executable { Assertions.assertEquals(product.sku,"sku-cem-001") },
            Executable { Assertions.assertEquals(product.description,"bolsa 50 kg") },
            Executable { Assertions.assertEquals(product.price,25000.0) },
            Executable { Assertions.assertEquals(product.hasDiscount, false) }
        )
    }

    @Test
    @DisplayName("Get product Info")
    @Throws(Exception::class)
    fun getProductInfo() {
        val productToCreate = CreateOrEditProductDTO(
            "cemento Argos",
            "sku-cem-001",
            "bolsa 50 kg",
            25000.0,
            false
        )
        val productCreated = productFacade!!.createProduct(productToCreate)
        val productId = UUID.fromString(productCreated.productId)
        val productDetails = productFacade.getProductById(productId)
        Assertions.assertAll(
            Executable {
                if(productDetails != null){
                    Executable { Assertions.assertEquals(productDetails.productId,productId) }
                    Executable { Assertions.assertEquals(productDetails.name,"cemento Argos") }
                    Executable { Assertions.assertEquals(productDetails.sku,"sku-cem-001") }
                    Executable { Assertions.assertEquals(productDetails.description,"bolsa 50 kg") }
                    Executable { Assertions.assertEquals(productDetails.price,25000.0) }
                    Executable { Assertions.assertEquals(productDetails.hasDiscount, false) }
                }
            }
        )
    }


    @Test
    @DisplayName("Edit a product")
    @Throws(Exception::class)
    fun editAProduct() {
        val productToCreate = CreateOrEditProductDTO(
            "cemento Argos",
            "sku-cem-001",
            "bolsa 50 kg",
            25000.0,
            false
        )
        val productCreated = productFacade!!.createProduct(productToCreate)
        val productId = UUID.fromString(productCreated.productId)
        val productToUpdate = CreateOrEditProductDTO(
            "cemento Diamante",
            "sku-cem-002",
            "bolsa 25 kg",
            12000.0,
            true
        )

        val productUpdated = productFacade.updateProduct(productId, productToUpdate)
        Assertions.assertAll(
            Executable {
                if(productUpdated != null){
                    Executable { Assertions.assertEquals(productUpdated.productId,productId) }
                    Executable { Assertions.assertEquals(productUpdated.name,"cemento Diamante") }
                    Executable { Assertions.assertEquals(productUpdated.sku,"sku-cem-002") }
                    Executable { Assertions.assertEquals(productUpdated.description,"bolsa 25 kg") }
                    Executable { Assertions.assertEquals(productUpdated.price,12000.0) }
                    Executable { Assertions.assertEquals(productUpdated.hasDiscount, true) }
                }
            }
        )
    }

    @Test
    @DisplayName("Delete a product")
    @Throws(Exception::class)
    fun deleteAProduct() {
        val productToCreate = CreateOrEditProductDTO(
            "cemento Argos",
            "sku-cem-001",
            "bolsa 50 kg",
            25000.0,
            false
        )
        val productCreated = productFacade!!.createProduct(productToCreate)
        val productId = UUID.fromString(productCreated.productId)
        val productDeleted = productFacade.deleteProduct(productId)
        Assertions.assertAll(
            Executable { Assertions.assertEquals(productDeleted.statusCode, HttpStatus.OK)}
        )
    }
}