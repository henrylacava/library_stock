package com.henrylacava.library_stock.dto;

import java.util.List;

public record CustomerResponseDto(List<CustomerDto> customers, Long totalElements, Integer totalPages, Boolean isFirst,
                                  Boolean isLast, Integer page) {
}
