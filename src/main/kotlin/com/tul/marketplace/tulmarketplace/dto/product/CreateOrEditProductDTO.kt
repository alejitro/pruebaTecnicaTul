package com.tul.marketplace.tulmarketplace.dto.product

import lombok.*

@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
class CreateOrEditProductDTO {
    private val name: String? = null
    private val sku: String? = null
    private val description: String? = null
    private val price: Double? = null
    private val hasDiscount: Boolean = false
}