package acc.microservices.products.services.interfaces;

import java.util.List;

import acc.microservices.products.model.Category;
import acc.microservices.products.model.Product;

public interface ProductService {
  public List<Product> listAllProducts();

  public Product getProductById(Long id);

  public Product createProduct(Product product);

  public Product updateProduct(Product product);

  public Product deleteProduct(Long id);

  public List<Product> findByCategory(Category category);

  public Product updateStock(Long id, Integer qty);
}
