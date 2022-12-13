package com.retro.visionarycrofting.ressources;

import com.retro.visionarycrofting.entities.Cart;
import com.retro.visionarycrofting.services.CartService;
import com.retro.visionarycrofting.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GeneralController {
  @Autowired
  private CartService cartService;
  @Autowired
  private ProductService productService;
  @ModelAttribute
  public void populateModel(Model model, HttpServletRequest request) {
    String sessionToken = (String) request.getSession(true).getAttribute("sessiontToken");
    if(sessionToken == null) {
      model.addAttribute("shoppingCart", new Cart());
    }
    else {
      model.addAttribute("shoppingCart", cartService.getShoppingCartBySessionToken(sessionToken));
    }
  }
}
