package lucca.shizuru.preTest.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lucca.shizuru.preTest.dtos.ValidationCategoryDto;
import lucca.shizuru.preTest.models.CategoryModel;
import lucca.shizuru.preTest.repositories.CategoryRepository;
import lucca.shizuru.preTest.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController {
    @Autowired
    private final CategoryService categoryService;


    @PostMapping
    public ResponseEntity<CategoryModel> createCategory(@Valid @RequestBody ValidationCategoryDto validationCategoryDto) {
        return ResponseEntity.ok(categoryService.createCategory(validationCategoryDto));
    }
    @GetMapping
    public ResponseEntity<List<CategoryModel>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoryModel> getCategoryById(@PathVariable UUID id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<CategoryModel> updateCategory(@PathVariable UUID id,
                                                        @Valid @RequestBody ValidationCategoryDto validationCategoryDto) {
        return ResponseEntity.ok(categoryService.updateCategory(id, validationCategoryDto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryModel> deleteCategoryById(@PathVariable UUID id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

}
