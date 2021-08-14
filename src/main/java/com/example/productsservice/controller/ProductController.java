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

//    @GetMapping("product/{articleNumber")
//    public Product getProductByArticleNumber(@PathVariable String articleNumber) {
//        return this.productRepository.findProductByArticleNumber(articleNumber);
//    }

//    @GetMapping("/products")
//    public List<Product> getAllProducts() {
//        return this.productRepository.findAll();
//    }
//

//    @GetMapping("/category")
//    public List<Product> cat() {
//
//        return productRepository.findAllByCategory();
//    }

    //Get specific product from specific store
    @GetMapping("/store/{storeName}/article/{articleNumber}")
    public Product getProductByStoreNameAndArticleNumber(@PathVariable String storeName, @PathVariable String articleNumber) {
        return productRepository.findProductsByStoreNameAndArticleNumber(storeName, articleNumber);
    }

    //Get all products from specific store
    @GetMapping("/products/{storeName}")
    public List<Product> getProductsByStoreName(@PathVariable String storeName) {

        return productRepository.findProductsByStoreName(storeName);
    }

    //Get all procuts from specific category
    @GetMapping("/store/{storeName}/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable String storeName, @PathVariable String category) {

        return productRepository.findProductByStoreNameAndAndCategory(storeName, category);
    }

//
//    //Add product
//    @PostMapping("/product/{storeName}")
//    public Product addProduct(@PathVariable String storeName, @RequestBody Product product) {
//
//        Product newProduct = new Product();
//
//        newProduct.setStoreName(storeName);
//        newProduct.setName((product.getName()));
//        newProduct.setCategory((product.getCategory()));
//        newProduct.setArticleNumber((product.getArticleNumber()));
//        newProduct.setDelivery((product.getDelivery()));
//        newProduct.setMaterial((product.getMaterial()));
//        newProduct.setMaintenance(product.getMaintenance());
//        newProduct.setEnvironment((product.getEnvironment()));
//        newProduct.setStock((product.getStock()));
//        newProduct.setPrice((product.getPrice()));
//        newProduct.setSize((product.getSize()));
//
//        productRepository.save(newProduct);
//
//        return newProduct;
//    }

    //Add product
    @PostMapping("/product")
    public Product addProduct(@RequestBody Product product) {

        productRepository.save(product);

        return product;
    }

//    @PutMapping("/product")
//    public Product updateProduct(@RequestBody Product updatedProduct) {
//        Product retrivedProduct = productRepository.findProductsByStoreNameAndArticleNumber(updatedProduct.getStoreName(), updatedProduct.getArticleNumber());
//
//        retrivedProduct.setStoreName((updatedProduct.getStoreName()));
//        retrivedProduct.setName((updatedProduct.getName()));
//        retrivedProduct.setCategory((updatedProduct.getCategory()));
//        retrivedProduct.setArticleNumber((updatedProduct.getArticleNumber()));
//        retrivedProduct.setDelivery((updatedProduct.getDelivery()));
//        retrivedProduct.setMaterial((updatedProduct.getMaterial()));
//        retrivedProduct.setMaintenance(updatedProduct.getMaintenance());
//        retrivedProduct.setEnvironment((updatedProduct.getEnvironment()));
//        retrivedProduct.setStock((updatedProduct.getStock()));
//        retrivedProduct.setPrice((updatedProduct.getPrice()));
//        retrivedProduct.setSize((updatedProduct.getSize()));
//
//        productRepository.save(retrivedProduct);
//
//        return retrivedProduct;
//    }

    //Update specific product from specific store
    @PutMapping("/store/{storeName}/article/{articleNumber}")
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

    //Delete specific product from specific store
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
