package acc.microservices.products.repository;

import java.util.Date;
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
    Product p1 = Product.builder()
        .category(Category.builder().id(1L).build())
        .name("Computer")
        .description("")
        .stock(15)
        .createdAt(new Date())
        .price(1225.5)
        .status("Created")
        .build();
    this.productRepository.save(p1);

    List<Product> listProducts = this.productRepository.findByCategory(p1.getCategory());
    Assertions.assertThat(listProducts.size()).isEqualTo(3);
  }
}
