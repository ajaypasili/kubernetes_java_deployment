package uk.co.danielbryant.djshopping.stockmanager.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.co.danielbryant.djshopping.stockmanager.model.Stock;
import uk.co.danielbryant.djshopping.stockmanager.services.StockService;
import java.util.List;

@RestController
@RequestMapping("/stocks")
public class StockResource {
    private final StockService stockService;
    public StockResource(StockService stockService) { this.stockService = stockService; }

    @GetMapping
    public List<Stock> getStocks() { return stockService.getStocks(); }

    @GetMapping("/{productId}")
    public ResponseEntity<Stock> getStock(@PathVariable String productId) {
        return stockService.findStock(productId).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
