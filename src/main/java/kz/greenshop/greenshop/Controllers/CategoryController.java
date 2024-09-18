package kz.greenshop.greenshop.Controllers;

import kz.greenshop.greenshop.Repositories.CategoryRepository;
import kz.greenshop.greenshop.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/add_category")
    public String addCategory() {
        return "add_category";
    }

    @PostMapping("/add_category")
    public String addCategory(@RequestParam(name = "name") String name) {
        categoryService.createCategory(name);
        return "redirect:/add_category";
    }


}
