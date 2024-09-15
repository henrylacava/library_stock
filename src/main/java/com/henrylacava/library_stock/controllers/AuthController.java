package com.henrylacava.library_stock.controllers;

import com.henrylacava.library_stock.dto.AuthResponseDto;
import com.henrylacava.library_stock.dto.LoginDto;
import com.henrylacava.library_stock.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto) {
        return new ResponseEntity<>(authService.login(loginDto), HttpStatus.OK);
    }

    @GetMapping("validate")
    public ResponseEntity<String> validate() {
        return new ResponseEntity<>("Token validated", HttpStatus.OK);
    }
}
