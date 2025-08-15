package com.leonardoramos.keycloak.keycloak.repository;

import com.leonardoramos.keycloak.keycloak.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
