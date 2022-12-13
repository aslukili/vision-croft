package com.retro.visionarycrofting.ressources;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.retro.visionarycrofting.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class CartController {

  @Autowired
  private CartService cartService;

  @PostMapping("/addToCart")
  public String addToCart(
    HttpServletRequest request,
    Model model,
    @RequestParam("id") Long id,
    @RequestParam("quantity") int quantity) {

    // sessiontToken
    String sessionToken = (String) request.getSession(true).getAttribute("sessiontToken");
    if (sessionToken == null) {
      sessionToken = UUID.randomUUID().toString();
      request.getSession().setAttribute("sessiontToken", sessionToken);
      cartService.addShoppingCartFirstTime(id, sessionToken, quantity);
    } else {
      cartService.addToExistingShoppingCart(id, sessionToken, quantity);
    }
    return "redirect:/";
  }

  @GetMapping("/shoppingCart")
  public String showShoopingCartView(HttpServletRequest request, Model model) {
    return "shopping-cart";
  }

  // TODO front-end
  @GetMapping("/removeCartItem/{id}")
  public String removeItem(@PathVariable("id") Long id, HttpServletRequest request) {
    String sessionToken = (String) request.getSession(false).getAttribute("sessiontToken");
    System.out.println("got here ");
    cartService.removeCartIemFromShoppingCart(id,sessionToken);
    return "redirect:/shoppingCart";
  }

  //TODO front-end
  @GetMapping("/clearShoppingCart")
  public String clearShoopiString(HttpServletRequest request) {
    String sessionToken = (String) request.getSession(false).getAttribute("sessiontToken");
    request.getSession(false).removeAttribute("sessiontToken");
    cartService.clearShoppingCart(sessionToken);
    return "redirect:/shoppingCart";
  }
}
