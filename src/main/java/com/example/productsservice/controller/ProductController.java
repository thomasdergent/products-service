package com.example.productsservice.controller;

import com.example.productsservice.model.Product;
import com.example.productsservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    @GetMapping("/products/{storeName}")
    public List<Product> getProductsByStoreName(@PathVariable String storeName) {

        return productRepository.findProductsByStoreName(storeName);
    }

    @GetMapping("/products/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable String category) {

        return productRepository.findProductsByCategory(category);
    }

    @GetMapping("/store/{storeName}/article/{articleNumber}")
    public Product getProductByStoreNameAndArticleNumber(@PathVariable String storeName, @PathVariable String articleNumber) {
        return productRepository.findProductsByStoreNameAndArticleNumber(storeName, articleNumber);
    }

    @PostMapping("/product")
    public Product addProduct(@RequestBody Product product) {
        productRepository.save(product);

        return product;
    }

    @PutMapping("/product")
    public Product updateProduct(@RequestBody Product updatedProduct) {
        Product retrivedProduct = productRepository.findProductsByStoreNameAndArticleNumber(updatedProduct.getStoreName(), updatedProduct.getArticleNumber());

        retrivedProduct.setStoreName((updatedProduct.getStoreName()));
        retrivedProduct.setName((updatedProduct.getName()));
        retrivedProduct.setCategory((updatedProduct.getCategory()));
        retrivedProduct.setArticleNumber((updatedProduct.getArticleNumber()));
        retrivedProduct.setDelivery((updatedProduct.getDelivery()));
        retrivedProduct.setMaterial((updatedProduct.getMaterial()));
        retrivedProduct.setMaintenance(updatedProduct.getMaintenance());
        retrivedProduct.setEnvironment((updatedProduct.getEnvironment()));
        retrivedProduct.setStock((updatedProduct.getStock()));
        retrivedProduct.setPrice((updatedProduct.getPrice()));
        retrivedProduct.setSize((updatedProduct.getSize()));

        productRepository.save(retrivedProduct);

        return retrivedProduct;
    }

    @PutMapping("/edit/{storeName}/article/{articleNumber}")
    public Product edit(@PathVariable String storeName, @PathVariable String articleNumber, @RequestBody Product updatedProduct) {
        Product retrivedProduct = productRepository.findProductsByStoreNameAndArticleNumber(storeName, articleNumber);

        retrivedProduct.setStoreName(updatedProduct.getStoreName());
        retrivedProduct.setName((updatedProduct.getName()));
        retrivedProduct.setCategory((updatedProduct.getCategory()));
        retrivedProduct.setArticleNumber((updatedProduct.getArticleNumber()));
        retrivedProduct.setDelivery((updatedProduct.getDelivery()));
        retrivedProduct.setMaterial((updatedProduct.getMaterial()));
        retrivedProduct.setMaintenance(updatedProduct.getMaintenance());
        retrivedProduct.setEnvironment((updatedProduct.getEnvironment()));
        retrivedProduct.setStock((updatedProduct.getStock()));
        retrivedProduct.setPrice((updatedProduct.getPrice()));
        retrivedProduct.setSize((updatedProduct.getSize()));

        productRepository.save(retrivedProduct);

        return retrivedProduct;
    }

    @DeleteMapping("/product/store/{storeName}/article/{articleNumber}")
    public ResponseEntity deleteProduct(@PathVariable String storeName, @PathVariable String articleNumber) {
        Product product = productRepository.findProductsByStoreNameAndArticleNumber(storeName, articleNumber);

        if (product != null) {
            productRepository.delete(product);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
