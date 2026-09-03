package uk.co.danielbryant.djshopping.productcatalogue.model;
import java.math.BigDecimal;
public record Product(String id, String name, String description, BigDecimal price) {}
