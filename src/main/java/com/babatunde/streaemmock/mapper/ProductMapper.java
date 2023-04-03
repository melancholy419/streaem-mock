package com.babatunde.streaemmock.mapper;

import com.babatunde.streaemmock.dto.ProductDTO;
import com.babatunde.streaemmock.model.Product;

public class ProductMapper {

    public static Product toProduct(ProductDTO productDTO) {
        return new Product(productDTO.name(), productDTO.category(), productDTO.description(), productDTO.price(),
                productDTO.quantity());
    }

    public static ProductDTO toProductDTO(Product product) {
        return new ProductDTO(product.getId(), product.getName(), product.getDescription(), product.getCategory(),
                product.getPrice(), product.getQuantity());
    }

}
