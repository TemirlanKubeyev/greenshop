package kz.greenshop.greenshop.Services;

import kz.greenshop.greenshop.Models.Category;
import kz.greenshop.greenshop.Models.Product;
import kz.greenshop.greenshop.Models.Review;
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

    public List<Product> getFilteredProducts(String categoryId, String size) {
        if (categoryId == null && size == null || categoryId.equals("all") && size.equals("all")) {
            return productRepository.findAll();
        } else if (size.equals("all")) {
            Category category = categoryRepository.findById(Long.parseLong(categoryId)).orElseThrow();
            return productRepository.getProductsByCategory(category);
        } else if (categoryId.equals("all")) {
            Size[] sizes1 = Size.values();
            Size size1 = sizes1[Integer.parseInt(size)];
            return productRepository.getProductsBySize(size1);
        } else {
            Category category = categoryRepository.findById(Long.parseLong(categoryId)).orElseThrow();
            Size[] sizes1 = Size.values();
            Size size1 = sizes1[Integer.parseInt(size)];
            return productRepository.getProductsByCategoryAndSize(category, size1);
        }
    }

    public int getAverageScoreProduct(Product product) {
        int averageScore = 0;
        for (Review review : product.getReviews()) {
            averageScore += review.getScore();
        }
        return averageScore/product.getReviews().size();
    }

    public int getReviewQuantity(Product product) {
        return product.getReviews().size();
    }

    public List<Product> getRelatedProducts(Product product) {
        Category categoryId = product.getCategory();
        List<Product> relatedProducts = productRepository.getProductsByCategory(categoryId);
        relatedProducts.remove(product);
        return relatedProducts;
    }

}
