package com.example.productsservice.repository;

import com.example.productsservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    Product findProductByArticleNumber(String articleNumber);
    Product findProductsByStoreNameAndArticleNumber(String storeName, String articleNumber);
    List<Product> findProductsByCategory(String category);
    List<Product> findProductsByStoreName(String storeName);
}
