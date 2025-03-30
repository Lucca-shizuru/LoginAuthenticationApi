package lucca.shizuru.preTest.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lucca.shizuru.preTest.dtos.ValidationCategoryDto;
import lucca.shizuru.preTest.models.CategoryModel;
import lucca.shizuru.preTest.repositories.CategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryModel createCategory(@Valid ValidationCategoryDto validationCategoryDto) {
        CategoryModel categoryModel = new CategoryModel();
        BeanUtils.copyProperties(validationCategoryDto, categoryModel);
        return categoryRepository.save(categoryModel);
    }
    public List<CategoryModel> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<CategoryModel> getCategoryById(UUID id) {
        return categoryRepository.findById(id);
    }
    public CategoryModel updateCategoryById(UUID id, ValidationCategoryDto validationCategoryDto) {
        return categoryRepository.findById(id).map(existingCategory -> {
            BeanUtils.copyProperties(validationCategoryDto, existingCategory);
            return categoryRepository.save(existingCategory);
        }).orElseThrow(() -> new EntityNotFoundException("Category not found"));
    }
    public void deleteCategoryById(UUID id) {
        categoryRepository.deleteById(id);
    }
}
