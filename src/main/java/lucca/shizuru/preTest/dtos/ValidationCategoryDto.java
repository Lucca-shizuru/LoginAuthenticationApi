package lucca.shizuru.preTest.dtos;

import jakarta.validation.constraints.NotBlank;

public record ValidationCategoryDto(@NotBlank String categoryName,@NotBlank String categoryDescription) {
}
