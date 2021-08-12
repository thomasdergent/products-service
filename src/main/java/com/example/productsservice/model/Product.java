package com.example.productsservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public class Product {

    @Id
    private String id;
    private String storeName;
    private String name;
    private String category;
    private String articleNumber;
    private Boolean delivery;
    private String material;
    private String maintenance;
    private String environment;
    private int stock;
    private double price;
    private String size;

    public Product() {
    }

    public Product(String storeName, String name, String category, String articleNumber, Boolean delivery, String material, String maintenance, String environment, int stock, double price, String size) {
        setStoreName(storeName);
        setName(name);
        setCategory(category);
        setArticleNumber(articleNumber);
        setDelivery(delivery);
        setMaterial(material);
        setMaintenance(maintenance);
        setEnvironment(environment);
        setStock(stock);
        setPrice(price);
        setSize(size);
    }

    public String getId(){
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStoreName(){
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }


    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory(){
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getArticleNumber(){
        return articleNumber;
    }

    public void setArticleNumber(String articleNumber) {
        this.articleNumber = articleNumber;
    }

    public Boolean getDelivery(){
        return delivery;
    }

    public void setDelivery(Boolean delivery) {
        this.delivery = delivery;
    }

    public String getMaterial(){
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getMaintenance(){
        return maintenance;
    }

    public void setMaintenance(String maintenance) {
        this.maintenance = maintenance;
    }

    public String getEnvironment(){
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public int getStock(){
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSize(){
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
