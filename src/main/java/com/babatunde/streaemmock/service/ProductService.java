package com.babatunde.streaemmock.service;

import com.babatunde.streaemmock.dto.FetchRequestDTO;
import com.babatunde.streaemmock.dto.FetchResponseDTO;
import com.babatunde.streaemmock.dto.ProductDTO;
import com.babatunde.streaemmock.dto.UpdateStockLevelRequestDTO;
import com.babatunde.streaemmock.exceptions.ResourceNotFoundException;
import com.babatunde.streaemmock.model.Product;
import com.babatunde.streaemmock.repository.ProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

import static com.babatunde.streaemmock.mapper.ProductMapper.toProduct;
import static com.babatunde.streaemmock.mapper.ProductMapper.toProductDTO;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductApiService productApiService;

    public ProductService(ProductRepository productRepository, ProductApiService productApiService) {
        this.productRepository = productRepository;
        this.productApiService = productApiService;
    }

    public void init() {
        var data = productApiService.fetchProduct().subscribe((product) ->productRepository.save(toProduct(product)));
        if (data.isDisposed())
            data.dispose();
    }

    public FetchResponseDTO<ProductDTO> getProducts(FetchRequestDTO fetchRequestDTO) {

        var pageRequest = fetchRequestDTO.toPageRequest();
        var pagedProducts = productRepository.findAll(pageRequest);
        var products = pagedProducts.getContent().stream().map(p -> toProductDTO(p)).collect(Collectors.toList());
        return new FetchResponseDTO<>(pagedProducts.getTotalPages(), pagedProducts.getTotalElements(),
                products);
    }

    private Product getProductById(long id){
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No product found for request"));

    }

    public ProductDTO findById(long id) {
        Product product = getProductById(id);
        return toProductDTO(product);
    }
    public FetchResponseDTO<ProductDTO> findByCategory(String category,
                                                       FetchRequestDTO fetchRequest) {
        PageRequest pageRequest = fetchRequest.toPageRequest();
       var pagedProducts =  productRepository.findByCategory(category,pageRequest);
        var products = pagedProducts.getContent().stream()
                .map(p->toProductDTO(p)).collect(Collectors.toList());
        return new FetchResponseDTO<>(pagedProducts.getTotalPages(),
                pagedProducts.getTotalElements(),
                products);
    }

    public ProductDTO updateProductQuantity(UpdateStockLevelRequestDTO updateStockLevelRequest) {
        Product product = getProductById(updateStockLevelRequest.getProductId());
        product.setQuantity(updateStockLevelRequest.getQuantity());
        productRepository.save(product);
        return toProductDTO(product);
    }
}
