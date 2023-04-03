package com.babatunde.streaemmock.dto;

import java.math.BigDecimal;

public record ProductDTO(long id, String name, String description, String category, BigDecimal price, long quantity) {
}
