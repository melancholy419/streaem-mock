package com.babatunde.streaemmock.controller;

import com.babatunde.streaemmock.dto.FetchRequestDTO;
import com.babatunde.streaemmock.dto.FetchResponseDTO;
import com.babatunde.streaemmock.dto.ProductDTO;
import com.babatunde.streaemmock.service.ProductService;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;

import java.math.BigDecimal;
import java.util.Arrays;

import static com.babatunde.streaemmock.TestData.productsDTOs;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
//@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;



    @Test
    void getAllProducts() throws Exception {
        var response = new FetchResponseDTO<ProductDTO>(0,6, productsDTOs);
        when(productService.getProducts(Mockito.any(FetchRequestDTO.class))).thenReturn(response);
        LinkedMultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("size","1");
        params.add("page","0");
        params.add("direction","ASC");
        params.add("sortBy","id");
        mockMvc.perform(MockMvcRequestBuilders.get("/products")
                        .params(params))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalEntity").value(6))
                .andExpect(jsonPath("$.totalPage").value(0));
    }

    @Test
    void returnSuccessfulResponseWhenValidProductIdIsPassed() throws Exception {
        var response = new ProductDTO(1,"test1","test1","test1",new BigDecimal(100),10);
        when(productService.findById(1)).thenReturn(response);
        mockMvc.perform(MockMvcRequestBuilders.get("/products/{id}","1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("test1"))
                .andExpect(jsonPath("$.price").value(100));
    }

    @Test
    void findBCategoryWhenAValidCategoryIsPassed() throws Exception {
        var response = new FetchResponseDTO<ProductDTO>(0,1, Arrays.asList(productsDTOs.get(0)));
        when(productService.findByCategory(Mockito.anyString(),Mockito.any(FetchRequestDTO.class))).thenReturn(response);
        LinkedMultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("size","1");
        params.add("page","0");
        params.add("direction","ASC");
        params.add("sortBy","id");
        mockMvc.perform(MockMvcRequestBuilders.get("/products/category/{category}","test1").params(params))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalEntity").value(1))
                .andExpect(jsonPath("$.totalPage").value(0));

    }

    @Test
    void updateStockLevel() {
    }
}