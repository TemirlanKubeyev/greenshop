package kz.greenshop.greenshop.Services;

import kz.greenshop.greenshop.Models.Category;
import kz.greenshop.greenshop.Models.Product;
import kz.greenshop.greenshop.Models.enumaration.Size;
import kz.greenshop.greenshop.Repositories.CategoryRepository;
import kz.greenshop.greenshop.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public void createProduct(String name, int price, Size size, Long categoryId, MultipartFile photo) throws IOException {
        Category category = categoryRepository.findById(categoryId).orElseThrow();
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setSize(size);
        product.setCategory(category);

        String fileName = System.currentTimeMillis() + photo.getOriginalFilename();
        String directory = "C:\\Users\\Kasht\\IdeaProjects\\greenshop\\files\\";
        Path filePath = Path.of(directory + fileName);
        photo.getOriginalFilename();
        if (!(photo.isEmpty())) {
            Files.copy(photo.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            String pathPhoto = "files/" + fileName;
            product.setPhoto(pathPhoto);
        }
        productRepository.save(product);
    }

}
