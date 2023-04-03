package com.babatunde.streaemmock.service;

import com.babatunde.streaemmock.dto.FetchRequestDTO;
import com.babatunde.streaemmock.dto.FetchResponseDTO;
import com.babatunde.streaemmock.dto.ProductDTO;
import com.babatunde.streaemmock.model.Product;
import com.babatunde.streaemmock.repository.ProductRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductApiService productApiService;

    @InjectMocks
    private ProductService productService;


    @BeforeEach
    public void setUp(){

    }

    @Test
    void init() {

    }

    @Test
    void getProducts() {

        productRepository.saveAll(
                Arrays.asList(
                        new Product("test1","test1","test1",new BigDecimal(100),20)
                )
        );
        var fetchRequestDto = new FetchRequestDTO(0,1, Sort.Direction.ASC,"id");
        ProductDTO productDTO = new ProductDTO(1,"test1","test1","test1",new BigDecimal(100),20);

        when(productService.getProducts(fetchRequestDto)).thenReturn(new FetchResponseDTO<>(1,1, Arrays.asList(productDTO)));
    }

    @Test
    void findById() {
    }

    @Test
    void findByCategory() {
    }

    @Test
    void updateProductQuantity() {
    }
}