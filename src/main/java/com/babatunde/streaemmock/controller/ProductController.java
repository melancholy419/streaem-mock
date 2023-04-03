package com.babatunde.streaemmock.controller;

import com.babatunde.streaemmock.dto.FetchRequestDTO;
import com.babatunde.streaemmock.dto.FetchResponseDTO;
import com.babatunde.streaemmock.dto.ProductDTO;
import com.babatunde.streaemmock.dto.UpdateStockLevelRequestDTO;
import com.babatunde.streaemmock.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<FetchResponseDTO<ProductDTO>> getAllProducts(FetchRequestDTO fetchRequestDTO) {
        return ResponseEntity.ok(productService.getProducts(fetchRequestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<FetchResponseDTO<ProductDTO>> findBCategory(@PathVariable String category,
            FetchRequestDTO fetchRequest) {
        return ResponseEntity.ok(productService.findByCategory(category, fetchRequest));
    }

    @PutMapping
    public ResponseEntity<ProductDTO> updateStockLevel(
            @RequestBody @Valid UpdateStockLevelRequestDTO updateStockLevelRequest) {
        return ResponseEntity.ok(productService.updateProductQuantity(updateStockLevelRequest));
    }

}
