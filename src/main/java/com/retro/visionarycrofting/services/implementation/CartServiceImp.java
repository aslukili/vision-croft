package com.retro.visionarycrofting.services.implementation;

import com.retro.visionarycrofting.entities.Cart;
import com.retro.visionarycrofting.entities.CartItem;
import com.retro.visionarycrofting.entities.Product;
import com.retro.visionarycrofting.repositories.CartItemRepository;
import com.retro.visionarycrofting.repositories.CartRepository;
import com.retro.visionarycrofting.services.CartService;
import com.retro.visionarycrofting.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class CartServiceImp implements CartService {

  @Autowired
  private CartRepository shoppingCartRepository;
  @Autowired
  private ProductService productService;
  @Autowired
  CartItemRepository cartItemRepository;

  @Override
  public Cart getShoppingCartBySessionToken(String sessionToken) {
    return null;
  }

  @Override
  public Cart addShoppingCartFirstTime(Long id, String sessionToken, int quantity) {
    Cart shoppingCart = new Cart();
    CartItem cartItem = new CartItem();
    cartItem.setQuantity(quantity);
    cartItem.setDate(new Date());
    cartItem.setProduct(productService.getProductById(id));
    shoppingCart.getItems().add(cartItem);
    shoppingCart.setSessionToken(sessionToken);
    shoppingCart.setDate(new Date());
    return shoppingCartRepository.save(shoppingCart);
  }

  @Override
  public Cart addToExistingShoppingCart(Long id, String sessionToken, int quantity) {
    Cart shoppingCart = shoppingCartRepository.findCartBySessionToken(sessionToken);
    Product p = productService.getProductById(id);
    Boolean productDoesExistInTheCart = false;
    if (shoppingCart != null) {
      List<CartItem> items = shoppingCart.getItems();
      for (CartItem item : items) {
        if (item.getProduct().equals(p)) {
          productDoesExistInTheCart = true;
          item.setQuantity(item.getQuantity() + quantity);
          shoppingCart.setItems(items);
          return shoppingCartRepository.saveAndFlush(shoppingCart);
        }

      }
    }
    if(!productDoesExistInTheCart && (shoppingCart != null))
    {
      CartItem cartItem1 = new CartItem();
      cartItem1.setDate(new Date());
      cartItem1.setQuantity(quantity);
      cartItem1.setProduct(p);
      shoppingCart.getItems().add(cartItem1);
      return shoppingCartRepository.saveAndFlush(shoppingCart);
    }

    return this.addShoppingCartFirstTime(id, sessionToken, quantity);
  }

  @Override
  public void removeCartIemFromShoppingCart(Long id, String sessionToken) {

  }

  @Override
  public void clearShoppingCart(String sessionToken) {

  }
}
