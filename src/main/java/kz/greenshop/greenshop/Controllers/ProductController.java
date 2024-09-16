package kz.greenshop.greenshop.Controllers;


import kz.greenshop.greenshop.Models.Product;
import kz.greenshop.greenshop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public String getProducts(Model model) {
        List<Product> productList = productRepository.findAll();
        for (Product product : productList) {
            System.out.println(product.getCategory().getName());
            System.out.println(product.getCategory());
            System.out.println(product.getSize());
            System.out.println(product.getPrice());
        }
        model.addAttribute("products", productList);
        return "products";
    }

}
