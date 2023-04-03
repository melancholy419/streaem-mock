package com.babatunde.streaemmock.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class UpdateStockLevelRequestDTO {

    @Min(value=1,message = "productId cannot be less than 1")
    private long productId;

    @Min(value=1,message = "quantity cannot be less than 1")
    private long quantity;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
