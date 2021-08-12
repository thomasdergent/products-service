package com.example.productsservice.repository;

import com.example.productsservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    Product findProductsByStoreNameAndArticleNumber(String storeName, String articleNumber);
}
