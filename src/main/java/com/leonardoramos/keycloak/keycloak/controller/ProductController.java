package com.leonardoramos.keycloak.keycloak.controller;
import com.leonardoramos.keycloak.keycloak.entity.Product;
import com.leonardoramos.keycloak.keycloak.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(@AuthenticationPrincipal Jwt jwt) {
        System.out.println("Listagem de produtos acessada por: " + jwt.getClaimAsString("preferred_username"));
        return ResponseEntity.ok(productRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product, @AuthenticationPrincipal Jwt jwt) {
        System.out.println("Produto '" + product.getName() + "' criado por usuário: " + jwt.getClaimAsString("preferred_username"));
        Product savedProduct = productRepository.save(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }
}
