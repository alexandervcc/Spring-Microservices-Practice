package acc.microservices.products.services;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import acc.microservices.products.exceptions.NotFoundException;
import acc.microservices.products.model.Category;
import acc.microservices.products.model.Product;
import acc.microservices.products.repository.ProductRepository;
import acc.microservices.products.services.interfaces.ProductService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
  private final ProductRepository productRepository;

  @Override
  public Product createProduct(Product product) {
    product.setStatus("CREATED");
    product.setCreatedAt(new Date());
    return this.productRepository.save(product);
  }

  @Override
  public Product deleteProduct(Long id) {
    Product product = this.getProductById(id);
    product.setStatus("DELETED");
    return this.productRepository.save(product);
  }

  @Override
  public List<Product> findByCategory(Category category) {
    return this.productRepository.findByCategory(category);
  }

  @Override
  public Product getProductById(Long id) {
    return this.productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));
  }

  @Override
  public List<Product> listAllProducts() {
    return this.productRepository.findAll();
  }

  @Override
  public Product updateProduct(Product product) {
    Product productToUpdate = this.getProductById(product.getId());
    productToUpdate.setDescription(product.getDescription());
    productToUpdate.setName(product.getName());
    productToUpdate.setPrice(product.getPrice());
    productToUpdate.setStock(product.getStock());
    productToUpdate.setStatus("UPDATED");
    return this.productRepository.save(productToUpdate);
  }

  @Override
  public Product updateStock(Long id, Integer qty) {
    Product product = this.getProductById(id);
    product.setStock(qty);
    return this.productRepository.save(product);
  }

}
