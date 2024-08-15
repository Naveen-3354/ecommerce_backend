package com.ecommerce.backend.services.privateServices;

import com.ecommerce.backend.models.entity.Category;
import com.ecommerce.backend.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    public final CategoryRepository repository;

    public String insertCategory(Category category) {
        category.setCreatedOn(LocalDate.now());
        repository.save(category);
        return "Category added.";
    }

    public String insertManyCategorys(List<Category> categorys) {
        repository.saveAll(categorys);
        return "Categorys added.";
    }

    public List<Category> selectAll() {
        return repository.findAll();
    }

    public Optional<Category> selectById(long id) {
        return repository.findById(id);
    }

    public String updateCategory(long id, Category category) {
        Optional<Category> categoryExist = repository.findById(id);
        if (categoryExist.isPresent()) {
            Category existingCategory = categoryExist.get();
            existingCategory.setName(category.getName());
            repository.save(existingCategory);
            return "Category Updated.";
        }
        return "category does not exist.";
    }

    public String deleteCategory(long id) {
        repository.deleteById(id);
        return "Category deleted.";
    }
}
