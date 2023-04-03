package com.babatunde.streaemmock.service;

import com.babatunde.streaemmock.dto.ProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;

@Service
public class ProductApiService {

    private final WebClient webClient;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public ProductApiService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<ProductDTO> fetchProduct() {
        var responseSpec = webClient.get().retrieve().bodyToFlux(ProductDTO.class)
                .onErrorResume(WebClientResponseException.class, ex -> {
                    var statusCode = ex.getStatusCode();
                    log.error("{} Exception occurred while trying to fetch product: {}", statusCode, ex.getCause());
                    return Flux.empty();
                });
        return responseSpec;
    }

}
