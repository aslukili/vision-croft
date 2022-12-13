package com.retro.visionarycrofting.services;

import com.retro.visionarycrofting.entities.Cart;

public interface CartService {
  Cart getShoppingCartBySessionToken(String sessionToken);

  Cart addShoppingCartFirstTime(Long id, String sessionToken, int quantity);

  Cart addToExistingShoppingCart(Long id, String sessionToken, int quantity);

  void removeCartIemFromShoppingCart(Long id, String sessionToken);

  void clearShoppingCart(String sessionToken);
}
