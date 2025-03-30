package lucca.shizuru.preTest.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name= "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID categoryId;
    private String categoryName;
    private String categoryDescription;

}
