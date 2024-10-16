package kz.greenshop.greenshop.Controllers;


import kz.greenshop.greenshop.Models.Cart;
import kz.greenshop.greenshop.Models.Category;
import kz.greenshop.greenshop.Models.Product;
import kz.greenshop.greenshop.Models.Review;
import kz.greenshop.greenshop.Models.enumaration.Size;
import kz.greenshop.greenshop.Repositories.CartRepository;
import kz.greenshop.greenshop.Repositories.CategoryRepository;
import kz.greenshop.greenshop.Repositories.ProductRepository;
import kz.greenshop.greenshop.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private CartRepository cartRepository;

    @Autowired
    private ProductService productService;


    @GetMapping("/products")
    public String getProducts(Model model, @RequestParam(value = "category", required = false) String categoryId,
                              @RequestParam(value = "size", required = false) String size) {
        List<Product> products = productService.getFilteredProducts(categoryId, size);
        model.addAttribute("products", products);
        if (products.isEmpty()) {
            String empty = "товаров по таким параметрам отсутствуют!";
            model.addAttribute("emptyProducts", empty);
        }
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        Size[] sizes = Size.values();
        model.addAttribute("sizes", sizes);
        int smallProducts = productRepository.getProductsBySize(Size.Small).size();
        int mediumProducts = productRepository.getProductsBySize(Size.Medium).size();
        int largeProducts = productRepository.getProductsBySize(Size.Large).size();
        model.addAttribute("small", smallProducts);
        model.addAttribute("medium", mediumProducts);
        model.addAttribute("large", largeProducts);
        return "products";
    }

    @PostMapping("/products")
    public String getProduct(@RequestParam(name = "category", required = false, defaultValue = "all") String categoryId,
                             @RequestParam(name = "size", required = false, defaultValue = "all") String size) {
        return "redirect:/products?category=" + categoryId + "&size=" + size;
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

    @GetMapping("/product_view/{id}")
    public String productView(@PathVariable(value = "id") long id,
            @RequestParam(name = "quantity", defaultValue = "1") int quantity, Model model) {
        Product product = productRepository.findById(id).orElseThrow();
        model.addAttribute("product", product);
        double averageScoreProduct = productService.getAverageScoreProduct(product);
        model.addAttribute("averageScore", averageScoreProduct);
        int reviewQuantity = productService.getReviewQuantity(product);
        model.addAttribute("reviewQuantity", reviewQuantity);
        List<Product> relatedProducts = productService.getRelatedProducts(product);
        model.addAttribute("relatedProducts", relatedProducts);
        model.addAttribute("quantity", quantity);
        model.addAttribute("sizes", productService.getSizes());
        return "product_view";
    }

    @PostMapping("/product_view/{id}/inc")
    public String increaseQuantity(@PathVariable(value = "id") long id,
                                   @RequestParam(name = "quantity") int quantity) {
        quantity++;
        return "redirect:/product_view/" + id + "?quantity=" + quantity;
    }

    @PostMapping("/product_view/{id}/dec")
    public String decreaseQuantity(@PathVariable(value = "id") long id,
            @RequestParam(name = "quantity") int quantity) {
        quantity--;
        if (quantity==0) {
            return "redirect:/product_view/" + id;
        }
        return "redirect:/product_view/" + id +"?quantity=" + quantity;
    }

}


