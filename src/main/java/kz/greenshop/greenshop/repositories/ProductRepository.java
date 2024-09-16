package kz.greenshop.greenshop.repositories;

import kz.greenshop.greenshop.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
