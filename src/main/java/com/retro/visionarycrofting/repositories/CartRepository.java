package com.retro.visionarycrofting.repositories;

import com.retro.visionarycrofting.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
  Cart findCartBySessionToken(String sessionToken);
}
