package acc.microservices.shopping.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import acc.microservices.shopping.model.pojos.Product;

@FeignClient(name = "product-service")
public interface ProductClient {
  final String PRODUCT_PATH = "/api/v1/product";

  @GetMapping(path = PRODUCT_PATH + "/{id}")
  public ResponseEntity<Product> getProductById(@PathVariable(name = "id") Long productId);

  @PutMapping(path = PRODUCT_PATH + "/{id}/stock")
  public ResponseEntity<Product> updateProductStock(
      @PathVariable(name = "id") Long productId,
      @RequestParam(name = "qty", required = true) Integer qty);
}
