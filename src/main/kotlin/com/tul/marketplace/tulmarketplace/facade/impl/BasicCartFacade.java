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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class BasicCartFacade implements CartFacade {

    @Autowired
    private final CartService cartService;

    @Autowired
    private final ProductFacade productFacade;

    @NotNull
    @Override
    public CartDTO createCart(@NotNull ProductToCartDTO productToCartDTO) throws Exception {
        var carCreated = cartService.createCart();
        System.out.println("carCreated: " + carCreated);
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
        var productsInCar = cartService.listProductsOnCart(carCreated.getCarId());
        return CartConverter.toCartDTO(carCreated,new CartProductsDTO(productsInCar));
    }

    @Nullable
    @Override
    public CartProductsDTO listCartProducts(@NotNull UUID cartId) throws Exception {
        var productsInCart = cartService.listProductsOnCart(cartId);
        return new CartProductsDTO(productsInCart);
    }

    @Nullable
    @Override
    public CartDTO addProductToCart(@NotNull UUID cartId, @NotNull ProductToCartDTO productToCartDTO) throws Exception {
        List<ProductsOnCart> productsInCar = new ArrayList<>();
        var existingCart = cartService.getCart(cartId).orElseThrow(() -> new Exception ("Car does not exist"));
        var productToAdd = productFacade.getProductById(productToCartDTO.getProductId());
        assert productToAdd != null;
        var existingProductsInCar = cartService.listProductsOnCart(cartId);
        existingProductsInCar.forEach(item -> {
            productsInCar.add(item);
            if(!item.getProductId().equals(productToCartDTO.getProductId())){
                ProductsOnCart product = new ProductsOnCart(
                        UUID.randomUUID(),
                        existingCart.getCarId(),
                        productToCartDTO.getProductId(),
                        productToCartDTO.getQuantity(),
                        productToAdd.getPrice()
                );
                cartService.addProductToCart(product);
            }
        });
        return CartConverter.toCartDTO(existingCart,new CartProductsDTO(productsInCar));
    }

    @Nullable
    @Override
    public CartDTO editProductInCart(@NotNull UUID cartId, @NotNull UUID productId, double quantity) throws Exception {
        return null;
    }

    @Nullable
    @Override
    public CartDTO removeProductsFromCart(@NotNull UUID cartId, @NotNull UUID productId) throws Exception {
        return null;
    }

    @NotNull
    @Override
    public ResponseEntity<String> deleteCart(@NotNull UUID cartId) throws Exception {
        return null;
    }

    @Nullable
    @Override
    public CartDTO checkoutCart(@NotNull UUID cartId) throws Exception {
        return null;
    }
}
