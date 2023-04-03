package com.babatunde.streaemmock.dto;

import com.babatunde.streaemmock.exceptions.InvalidFetchRequestException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public record FetchRequestDTO(int page, int size, Sort.Direction direction, String... sortBy) {
    public PageRequest toPageRequest() {
        if (page() < 0) {
            throw new InvalidFetchRequestException("Page must be at least 0");
        }
        if (size() > 200 || size < 0) {
            throw new InvalidFetchRequestException("The fetch size must be greater than 0 and not more than 100");
        }
        return PageRequest.of(page, size, direction, sortBy);
    }
}
