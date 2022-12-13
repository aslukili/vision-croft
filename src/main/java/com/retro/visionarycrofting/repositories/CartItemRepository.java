package com.retro.visionarycrofting.repositories;

import com.retro.visionarycrofting.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
