package kz.greenshop.greenshop.Services;

import kz.greenshop.greenshop.Controllers.CategoryController;
import kz.greenshop.greenshop.Models.Category;
import kz.greenshop.greenshop.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public void createCategory(String name) {
        Category category = new Category();
        category.setName(name);
        categoryRepository.save(category);
    }
}

