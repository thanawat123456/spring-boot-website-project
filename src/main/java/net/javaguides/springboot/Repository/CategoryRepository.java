package net.javaguides.springboot.Repository;

import net.javaguides.springboot.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
