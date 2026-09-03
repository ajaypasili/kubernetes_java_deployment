package uk.co.danielbryant.djshopping.productcatalogue.services;

import org.springframework.stereotype.Service;
import uk.co.danielbryant.djshopping.productcatalogue.model.Product;
import java.math.BigDecimal;
import java.util.*;

@Service
public class ProductService {
    private final Map<String, Product> products = Map.of(
        "1", new Product("1","Widget","Premium ACME Widgets",new BigDecimal("1.20")),
        "2", new Product("2","Sprocket","Grade B sprockets",new BigDecimal("4.10")),
        "3", new Product("3","Anvil","Large Anvils",new BigDecimal("45.50")),
        "4", new Product("4","Cogs","Grade Y cogs",new BigDecimal("1.80")),
        "5", new Product("5","Multitool","Multitools",new BigDecimal("154.10"))
    );
    public List<Product> getAllProducts() { return products.values().stream().sorted(Comparator.comparing(Product::id)).toList(); }
    public Optional<Product> getProduct(String id) { return Optional.ofNullable(products.get(id)); }
}
