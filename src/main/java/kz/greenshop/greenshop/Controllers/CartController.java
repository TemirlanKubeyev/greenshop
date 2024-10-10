package kz.greenshop.greenshop.Controllers;

import kz.greenshop.greenshop.Models.Cart;
import kz.greenshop.greenshop.Models.Product;
import kz.greenshop.greenshop.Repositories.CartRepository;
import kz.greenshop.greenshop.Repositories.CategoryRepository;
import kz.greenshop.greenshop.Repositories.ProductRepository;
import kz.greenshop.greenshop.Services.CartService;
import org.hibernate.validator.cfg.defs.Mod10CheckDef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CartController {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartService cartService;

    @GetMapping("/cart_shopping")
    public String getCart(Model model) {
        List<Cart> cartList = cartRepository.findAll();
        model.addAttribute("carts", cartList);
        return "cart";
    }

    @PostMapping("/product_view/{id}")
    public String addToCartOrBuy(@PathVariable(value = "id") long id,
                           @RequestParam(name = "quantity") int quantity,
                            @RequestParam (name = "action") String action) {
        Product product = productRepository.findById(id).orElseThrow();
        if (action.equals("add_to_cart")) {
            cartService.addToCart(product, quantity);
        } else if (action.equals("buy_now")) {
            cartService.buyNow(product, quantity);
            return "redirect:/cart_shopping";
        }
        return "redirect:/product_view/" + id;
    }

}
