package acc.microservices.products.repository;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import acc.microservices.products.model.Category;
import acc.microservices.products.model.Product;

@DataJpaTest
public class ProductRepositoryTest {
  @Autowired
  private ProductRepository productRepository;

  @Test
  public void whenSearchByCategoryThenReturnProductsList() {

    List<Product> listProducts = this.productRepository.findByCategory(Category.builder().id(1L).build());
    Assertions.assertThat(listProducts.size()).isEqualTo(2);
  }
}
