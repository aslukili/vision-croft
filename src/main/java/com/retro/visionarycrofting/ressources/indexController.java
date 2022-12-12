package com.retro.visionarycrofting.ressources;

import com.retro.visionarycrofting.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexController {
  @Autowired
  private ProductService productService;
  @GetMapping("/")
  public String showIndex(Model model) {
    model.addAttribute("products", productService.findAll());
    return "index";
  }
}
