package com.babatunde.streaemmock.dto;

import java.util.List;

public record FetchResponseDTO<T>(int totalPage, long totalEntity, List<T> data) {

}
