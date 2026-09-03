package uk.co.danielbryant.djshopping.shopfront.repo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import uk.co.danielbryant.djshopping.shopfront.services.dto.StockDTO;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class StockRepo {
    private final String stockManagerUri;
    private final RestTemplate restTemplate;
    public StockRepo(@Value("${stock-manager-uri}") String stockManagerUri, RestTemplate restTemplate) {
        this.stockManagerUri=stockManagerUri; this.restTemplate=restTemplate;
    }
    public Map<String, StockDTO> getStockDTOs() {
        try {
            List<StockDTO> stocks=restTemplate.exchange(stockManagerUri+"/stocks", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<StockDTO>>(){}).getBody();
            if (stocks==null) return Collections.emptyMap();
            return stocks.stream().collect(Collectors.toMap(StockDTO::getProductId, Function.identity()));
        } catch (RestClientException ex) { return Collections.emptyMap(); }
    }
}
