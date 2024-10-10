package kz.greenshop.greenshop.Services;

import kz.greenshop.greenshop.Models.Cart;
import kz.greenshop.greenshop.Models.Product;
import kz.greenshop.greenshop.Repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public void addToCart(Product product, int quantity) {
        Cart cart = cartRepository.findCartByProduct(product);
        if (cart == null) {
            Cart newCart = new Cart();
            newCart.setQuantity(quantity);
            newCart.setProduct(product);
            cartRepository.save(newCart);
        } else {
            int sumQuantity = cart.getQuantity() + quantity;
            cart.setQuantity(sumQuantity);
            cartRepository.save(cart);
        }
    }

    public void buyNow (Product product, int quantity) {
        Cart cart = cartRepository.findCartByProduct(product);
        if (cart == null) {
            Cart newCart = new Cart();
            newCart.setQuantity(quantity);
            newCart.setProduct(product);
            cartRepository.save(newCart);
        }
    }

}
