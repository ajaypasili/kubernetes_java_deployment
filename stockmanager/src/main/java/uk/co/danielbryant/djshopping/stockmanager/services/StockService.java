package uk.co.danielbryant.djshopping.stockmanager.services;

import org.springframework.stereotype.Service;
import uk.co.danielbryant.djshopping.stockmanager.model.Stock;
import uk.co.danielbryant.djshopping.stockmanager.repositories.StockRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class StockService {
    private final StockRepository stockRepository;
    public StockService(StockRepository stockRepository) { this.stockRepository = stockRepository; }
    public List<Stock> getStocks() { return StreamSupport.stream(stockRepository.findAll().spliterator(), false).toList(); }
    public Optional<Stock> findStock(String productId) { return stockRepository.findById(productId); }
}
