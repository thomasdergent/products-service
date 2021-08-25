package com.example.productsservice.repository;

import com.example.productsservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findProductsByArticleNumber(String articleNumber);
    List<Product> findProductsByCategory(String category);
}
