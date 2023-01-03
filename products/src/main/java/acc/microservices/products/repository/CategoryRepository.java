package acc.microservices.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import acc.microservices.products.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
