package kz.greenshop.greenshop.Services;

import kz.greenshop.greenshop.Models.Category;
import kz.greenshop.greenshop.Models.Product;
import kz.greenshop.greenshop.Models.enumaration.Size;
import kz.greenshop.greenshop.Repositories.CategoryRepository;
import kz.greenshop.greenshop.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public void createProduct(String name, int price, Size size, Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow();
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setSize(size);
        product.setCategory(category);
        productRepository.save(product);
    }

}
