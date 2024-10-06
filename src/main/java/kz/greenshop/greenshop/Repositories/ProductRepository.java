package kz.greenshop.greenshop.Repositories;

import kz.greenshop.greenshop.Models.Category;
import kz.greenshop.greenshop.Models.Product;
import kz.greenshop.greenshop.Models.enumaration.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    public List<Product> getProductsByCategory(Category category);
    public List<Product> getProductsBySize(Size size);
    public List<Product> getProductsByCategoryAndSize(Category category, Size size);
}
