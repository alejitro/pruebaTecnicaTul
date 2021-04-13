package com.tul.marketplace.tulmarketplace.repository;

import com.tul.marketplace.tulmarketplace.model.ProductsOnCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductsOnCartRepository extends JpaRepository<ProductsOnCart, UUID> {
    Optional<ProductsOnCart>  findByCartId(UUID cartId);
    Optional<ProductsOnCart>  findByCartIdAndProductId(UUID cartId,UUID productID);
}
