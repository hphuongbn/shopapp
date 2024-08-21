package com.samsung.demojpa.controller;

import com.samsung.demojpa.entity.Products;
import com.samsung.demojpa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String welcome(final Model model) {
        List<Products> lstProducts = productService.getAllProducts();
        model.addAttribute("products", lstProducts);
        return "welcome";
    }

    @GetMapping("/home")
    public String home() {
        return "welcome";
    }

    @GetMapping("/search")
    public String searchProducts(@RequestParam("query") String query, Model model) {
        List<Products> products = productService.searchProducts(query);
        model.addAttribute("products", products);
        return "welcome";
    }
}
