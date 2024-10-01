package kz.greenshop.greenshop.Controllers;


import kz.greenshop.greenshop.Models.Category;
import kz.greenshop.greenshop.Models.Product;
import kz.greenshop.greenshop.Models.enumaration.Size;
import kz.greenshop.greenshop.Repositories.CategoryRepository;
import kz.greenshop.greenshop.Repositories.ProductRepository;
import kz.greenshop.greenshop.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public String getProducts(Model model) {
        List<Product> productList = productRepository.findAll();
        model.addAttribute("products", productList);
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        List<Product> smallProducts = productRepository.getProductsBySize(Size.Small);
        model.addAttribute("small", smallProducts.size());
        List<Product> mediumProducts = productRepository.getProductsBySize(Size.Medium);
        model.addAttribute("medium", mediumProducts.size());
        List<Product> largeProducts = productRepository.getProductsBySize(Size.Large);
        model.addAttribute("large", largeProducts.size());
        return "products";
    }

    @GetMapping("/add_product")
    public String addProduct(Model model) {
        model.addAttribute("sizes", Size.values());
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "add_products";
    }

    @PostMapping("/add_product")
    public String addProduct(@RequestParam(name = "name") String name,
                             @RequestParam(name = "price") int price,
                             @RequestParam(name = "size") Size size,
                             @RequestParam(name = "category_id") Long categoryId,
                             @RequestParam(name = "photo") MultipartFile photo) throws IOException {
        System.out.println(size);
        productService.createProduct(name, price, size, categoryId, photo);
        return "redirect:/add_product";
    }

}


