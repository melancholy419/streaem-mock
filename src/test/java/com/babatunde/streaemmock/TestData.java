package com.babatunde.streaemmock;

import com.babatunde.streaemmock.dto.ProductDTO;
import com.babatunde.streaemmock.model.Product;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class TestData {

    public static final List<ProductDTO> productsDTOs = Arrays.asList(
            new ProductDTO(1,"test1","test1","test1",new BigDecimal(100),10)
            ,new ProductDTO(2,"test2","test2","test2",new BigDecimal(200),20)
            ,new ProductDTO(3,"test3","test3","test3",new BigDecimal(300),30)
            ,new ProductDTO(4,"test4","test4","test4",new BigDecimal(400),40)
            ,new ProductDTO(5,"test5","test5","test5",new BigDecimal(500),50)
            ,new ProductDTO(6,"test6","test6","test6",new BigDecimal(600),60)
    );

    public static final List<Product> products = Arrays.asList(
            new Product("test1","test1","test1",new BigDecimal(100),10)
            ,new Product("test2","test2","test2",new BigDecimal(200),20)
            ,new Product("test3","test3","test3",new BigDecimal(300),30)
            ,new Product("test4","test4","test4",new BigDecimal(400),40)
            ,new Product("test5","test5","test5",new BigDecimal(500),50)
            ,new Product("test6","test6","test6",new BigDecimal(600),60)
    );
}
