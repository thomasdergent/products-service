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

    //Get specific product
    @GetMapping("product/{articleNumber}")
    public Product getProductByArticleNumber(@PathVariable String articleNumber) {
        return productRepository.findProductsByArticleNumber(articleNumber);
    }

    //Get all products
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    //Get all products from specific category
    @GetMapping("/products/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable String category) {

        return productRepository.findProductsByCategory(category);
    }

    //Add product
    @PostMapping("/product")
    public Product addProduct(@RequestBody Product product) {

        productRepository.save(product);

        return product;
    }

    //Update product
    @PutMapping("/product/{articleNumber}")
    public Product edit(@PathVariable String articleNumber, @RequestBody Product updatedProduct) {
        Product retrivedProduct = productRepository.findProductsByArticleNumber(articleNumber);

        retrivedProduct.setName((updatedProduct.getName()));
        retrivedProduct.setCategory((updatedProduct.getCategory()));
        retrivedProduct.setDescription((updatedProduct.getDescription()));
        retrivedProduct.setImage((updatedProduct.getImage()));
        retrivedProduct.setArticleNumber((updatedProduct.getArticleNumber()));
        retrivedProduct.setDelivery((updatedProduct.getDelivery()));
        retrivedProduct.setMaterial((updatedProduct.getMaterial()));
        retrivedProduct.setMaintenance(updatedProduct.getMaintenance());
        retrivedProduct.setEnvironment((updatedProduct.getEnvironment()));
        retrivedProduct.setPrice((updatedProduct.getPrice()));
        retrivedProduct.setSize((updatedProduct.getSize()));

        productRepository.save(retrivedProduct);

        return retrivedProduct;
    }

    //Delete product
    @DeleteMapping("/product/{articleNumber}")
    public ResponseEntity deleteProduct(@PathVariable String articleNumber) {
        Product product = productRepository.findProductsByArticleNumber(articleNumber);

        if (product != null) {
            productRepository.delete(product);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
//