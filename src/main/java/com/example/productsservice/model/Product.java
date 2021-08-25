package com.example.productsservice.model;

import javax.persistence.*;


@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")

    private int id;
    @Column(name="name")
    private String name;
    @Column(name="category")
    private String category;
    @Column(name="description")
    private String description;
    @Column(name="image")
    private String image;
    @Column(name="articleNumber")
    private String articleNumber;
    @Column(name="delivery")
    private Boolean delivery;
    @Column(name="material")
    private String material;
    @Column(name="maintenance")
    private String maintenance;
    @Column(name="environment")
    private String environment;
    @Column(name="price")
    private double price;
    @Column(name="size")
    private String size;

    public Product() {
    }

    public Product(String name, String category, String description, String image, String articleNumber, Boolean delivery, String material, String maintenance, String environment, double price, String size) {
        setName(name);
        setCategory(category);
        setDescription(description);
        setImage(image);
        setArticleNumber(articleNumber);
        setDelivery(delivery);
        setMaterial(material);
        setMaintenance(maintenance);
        setEnvironment(environment);
        setPrice(price);
        setSize(size);
    }

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription(){
        return description;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public String getImage(){
        return image;
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
