package com.example.l5_20233564.repository;

import com.example.l5_20233564.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
