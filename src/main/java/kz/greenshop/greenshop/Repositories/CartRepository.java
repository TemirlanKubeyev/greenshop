package kz.greenshop.greenshop.Repositories;

import kz.greenshop.greenshop.Models.Cart;
import kz.greenshop.greenshop.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findCartByProduct(Product product);

}
