package uk.co.danielbryant.djshopping.productcatalogue.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.co.danielbryant.djshopping.productcatalogue.model.Product;
import uk.co.danielbryant.djshopping.productcatalogue.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductResource {
    private final ProductService productService;
    public ProductResource(ProductService productService) { this.productService = productService; }

    @GetMapping
    public List<Product> getAllProducts() { return productService.getAllProducts(); }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable String id) {
        return productService.getProduct(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
