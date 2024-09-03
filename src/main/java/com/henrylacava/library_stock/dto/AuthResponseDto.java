package com.henrylacava.library_stock.dto;

public record AuthResponseDto(String accessToken, String tokenType) {
    public AuthResponseDto(String accessToken) {
        this(accessToken, "Bearer");
    }
}
