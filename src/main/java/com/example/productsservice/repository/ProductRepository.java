package com.example.productsservice.repository;

import com.example.productsservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    Product findProductsByStoreNameAndArticleNumber(String storeName, String articleNumber);
    List<Product> findProductByStoreNameAndAndCategory(String storeName, String category);
    List<Product> findProductsByStoreName(String storeName);
}
