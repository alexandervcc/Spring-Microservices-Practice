package acc.microservices.products.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import acc.microservices.products.exceptions.ValidationErrorsException;
import acc.microservices.products.model.Category;
import acc.microservices.products.model.Product;
import acc.microservices.products.services.interfaces.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/product")
@AllArgsConstructor
public class ProductController {
  private final ProductService productService;

  @GetMapping(path = "")
  public ResponseEntity<List<?>> listProducts(@RequestParam(required = false, name = "categoryId") Long categoryId) {
    List<Product> list = null;
    if (categoryId != null) {
      list = this.productService.findByCategory(Category.builder().id(categoryId).build());
    } else {
      list = this.productService.listAllProducts();
    }
    if (list.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(list);
    }
    return ResponseEntity.ok(list);
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<?> getProductById(@PathVariable(name = "id") Long productId) {
    Product list = this.productService.getProductById(productId);
    return ResponseEntity.ok(list);
  }

  @PostMapping
  public ResponseEntity<?> createProduct(@Valid @RequestBody Product productData, BindingResult validationResult) {
     if (validationResult.hasErrors()) {
      throw new ValidationErrorsException("Errors on sent attributes.", validationResult);
    }
    Product product = this.productService.createProduct(productData); 
    return ResponseEntity.status(HttpStatus.CREATED).body(product);
  }

  @PutMapping(path = "/{id}")
  public ResponseEntity<?> updateProduct(@PathVariable(name = "id") Long productId, @RequestBody Product productData) {
    productData.setId(productId);
    Product product = this.productService.updateProduct(productData);
    return ResponseEntity.ok(product);
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<?> deleteProductById(@PathVariable(name = "id") Long productId) {
    Product product = this.productService.deleteProduct(productId);
    return ResponseEntity.ok(product);
  }

  @PutMapping(path = "/{id}/stock")
  public ResponseEntity<?> updateProductStock(
      @PathVariable(name = "id") Long productId,
      @RequestParam(name = "qty", required = true) Integer qty) {
    Product product = this.productService.updateStock(productId, qty);
    return ResponseEntity.ok(product);
  }
}
