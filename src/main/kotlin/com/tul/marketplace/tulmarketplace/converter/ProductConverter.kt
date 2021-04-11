package com.tul.marketplace.tulmarketplace.converter

import com.tul.marketplace.tulmarketplace.model.Product
import com.tul.marketplace.tulmarketplace.dto.product.ProductDTO

object ProductConverter {
    @JvmStatic
    fun toProductDTO(product: Product): ProductDTO {

        return ProductDTO(
            productId = product.productId.toString(),
            name = product.name,
            sku = product.sku,
            description = product.description,
            price = product.price
        )
    }
}