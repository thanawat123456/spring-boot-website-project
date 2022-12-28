package net.javaguides.springboot.Repository;

import net.javaguides.springboot.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
	List<Product> findAllByCategory_id(int id);
	List<Product> findByName(String name);
}
