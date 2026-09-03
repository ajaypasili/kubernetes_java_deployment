package uk.co.danielbryant.djshopping.shopfront.repo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import uk.co.danielbryant.djshopping.shopfront.services.dto.ProductDTO;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ProductRepo {
    private final String productCatalogueUri; private final RestTemplate restTemplate;
    public ProductRepo(@Value("${product-catalogue-uri}") String productCatalogueUri, RestTemplate restTemplate) {
        this.productCatalogueUri=productCatalogueUri; this.restTemplate=restTemplate;
    }
    public Map<String, ProductDTO> getProductDTOs() {
        List<ProductDTO> products=restTemplate.exchange(productCatalogueUri+"/products",HttpMethod.GET,null,
          new ParameterizedTypeReference<List<ProductDTO>>(){}).getBody();
        if(products==null) return Collections.emptyMap();
        return products.stream().collect(Collectors.toMap(ProductDTO::getId,Function.identity()));
    }
}
