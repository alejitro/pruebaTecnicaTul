package com.tul.marketplace.tulmarketplace.facade.impl;

import com.tul.marketplace.tulmarketplace.converter.CartConverter;
import com.tul.marketplace.tulmarketplace.dto.car.CartProductDTO;
import com.tul.marketplace.tulmarketplace.dto.cart.CartDTO;
import com.tul.marketplace.tulmarketplace.dto.cart.CartProductsDTO;
import com.tul.marketplace.tulmarketplace.dto.cart.ProductToCartDTO;
import com.tul.marketplace.tulmarketplace.facade.CartFacade;
import com.tul.marketplace.tulmarketplace.facade.ProductFacade;
import com.tul.marketplace.tulmarketplace.model.ProductsOnCart;
import com.tul.marketplace.tulmarketplace.service.CartService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BasicCartFacade implements CartFacade {

    @Autowired
    private final CartService cartService;

    @Autowired
    private final ProductFacade productFacade;

    public BasicCartFacade(CartService cartService, ProductFacade productFacade) {
        this.cartService = cartService;
        this.productFacade = productFacade;
    }

    @NotNull
    @Override
    public CartDTO createCart(@NotNull ProductToCartDTO productToCartDTO) throws Exception {
        var carCreated = cartService.createCart();
        var productToAdd = productFacade.getProductById(productToCartDTO.getProductId());
        List<CartProductDTO> productInCar = new ArrayList<>();
        assert productToAdd != null;
        ProductsOnCart product = new ProductsOnCart(
                UUID.randomUUID(),
                carCreated.getCarId(),
                productToCartDTO.getProductId(),
                productToCartDTO.getQuantity(),
                productToAdd.getPrice()
        );
        cartService.addProductToCart(product);
        CartProductDTO cartProductDTO = new CartProductDTO(
                productToCartDTO.getProductId().toString(),
                productToAdd.getName(),
                productToCartDTO.getQuantity()
        );
        productInCar.add(cartProductDTO);
        return CartConverter.toCartDTO(carCreated,new CartProductsDTO(productInCar));
    }

    @Nullable
    @Override
    public CartProductsDTO listCartProducts(@NotNull UUID carId) throws Exception {
        return null;
    }

    @Nullable
    @Override
    public CartDTO addProductToCart(@NotNull UUID carId, @NotNull ProductToCartDTO productToCartDTO) throws Exception {
        return null;
    }

    @Nullable
    @Override
    public CartDTO editProductInCart(@NotNull UUID carId, @NotNull UUID productId, double quantity) throws Exception {
        return null;
    }

    @Nullable
    @Override
    public CartDTO removeProductsFromCart(@NotNull UUID carId, @NotNull UUID productId) throws Exception {
        return null;
    }

    @NotNull
    @Override
    public ResponseEntity<String> deleteCart(@NotNull UUID cartId) throws Exception {
        return null;
    }

    @Nullable
    @Override
    public CartDTO checkoutCart(@NotNull UUID carId) throws Exception {
        return null;
    }
}
