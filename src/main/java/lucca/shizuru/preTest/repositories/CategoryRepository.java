package lucca.shizuru.preTest.repositories;


import jdk.jfr.Category;
import lucca.shizuru.preTest.models.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryModel, UUID>{



}
