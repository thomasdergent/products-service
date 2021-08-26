package com.example.productsservice;

import com.example.productsservice.model.Product;
import com.example.productsservice.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerIntegrationTets {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    private Product product1 = new Product("Linmon chair", "Bureaustoel", "omschrijving", "afbeelding", "integration123", true, "Leer", "Spray", "Recycleerbaar", 500.00, "130cm");
    private Product product2 = new Product("Linmon desk", "Bureau", "omschrijving", "afbeelding", "integration456", true, "Hout", "Vochtbestendig", "Recycleerbaar", 75.00, "140cmx80cm");
    private Product product3 = new Product("Thomas desk", "Bureau", "omschrijving", "afbeelding", "123integration", true, "Karton", "Vochtbestendig", "Recycleerbaar", 75.00, "160cmx80cm");

    private Product productToBeDeleted = new Product("Alexa desk", "Bureau", "omschrijving", "afbeelding", "456integration", true, "Hout", "Vochtbestendig", "Recycleerbaar", 99.99, "150cmx80cm");

    @BeforeEach
    public void beforeAllTests() {
        productRepository.deleteAll();
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(productToBeDeleted);
    }

    @AfterEach
    public void afterAllTests() {

        productRepository.deleteAll();
    }

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void givenProduct_whenGetProductByArticleNumber_thenReturnJsonProduct() throws Exception {
        mockMvc.perform(get("/product/{articleNumber}", product1.getArticleNumber()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Linmon chair")))
                .andExpect(jsonPath("$.category", is("Bureaustoel")))
                .andExpect(jsonPath("$.description", is("omschrijving")))
                .andExpect(jsonPath("$.image", is("afbeelding")))
                .andExpect(jsonPath("$.articleNumber", is("integration123")))
                .andExpect(jsonPath("$.delivery", is(true)))
                .andExpect(jsonPath("$.material", is("Leer")))
                .andExpect(jsonPath("$.maintenance", is("Spray")))
                .andExpect(jsonPath("$.environment", is("Recycleerbaar")))
                .andExpect(jsonPath("$.price", is(500.00)))
                .andExpect(jsonPath("$.size", is("130cm")));
    }

    @Test
    public void givenProduct_whenGetProducts_thenReturnJsonProducts() throws Exception {

        mockMvc.perform(get("/products"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].name", is("Linmon chair")))
                .andExpect(jsonPath("$[0].category", is("Bureaustoel")))
                .andExpect(jsonPath("$[0].description", is("omschrijving")))
                .andExpect(jsonPath("$[0].image", is("afbeelding")))
                .andExpect(jsonPath("$[0].articleNumber", is("integration123")))
                .andExpect(jsonPath("$[0].delivery", is(true)))
                .andExpect(jsonPath("$[0].material", is("Leer")))
                .andExpect(jsonPath("$[0].maintenance", is("Spray")))
                .andExpect(jsonPath("$[0].environment", is("Recycleerbaar")))
                .andExpect(jsonPath("$[0].price", is(500.00)))
                .andExpect(jsonPath("$[0].size", is("130cm")))
                .andExpect(jsonPath("$[1].name", is("Linmon desk")))
                .andExpect(jsonPath("$[1].category", is("Bureau")))
                .andExpect(jsonPath("$[1].description", is("omschrijving")))
                .andExpect(jsonPath("$[1].image", is("afbeelding")))
                .andExpect(jsonPath("$[1].articleNumber", is("integration456")))
                .andExpect(jsonPath("$[1].delivery", is(true)))
                .andExpect(jsonPath("$[1].material", is("Hout")))
                .andExpect(jsonPath("$[1].maintenance", is("Vochtbestendig")))
                .andExpect(jsonPath("$[1].environment", is("Recycleerbaar")))
                .andExpect(jsonPath("$[1].price", is(75.00)))
                .andExpect(jsonPath("$[1].size", is("140cmx80cm")))
                .andExpect(jsonPath("$[2].name", is("Thomas desk")))
                .andExpect(jsonPath("$[2].category", is("Bureau")))
                .andExpect(jsonPath("$[2].description", is("omschrijving")))
                .andExpect(jsonPath("$[2].image", is("afbeelding")))
                .andExpect(jsonPath("$[2].articleNumber", is("123integration")))
                .andExpect(jsonPath("$[2].delivery", is(true)))
                .andExpect(jsonPath("$[2].material", is("Karton")))
                .andExpect(jsonPath("$[2].maintenance", is("Vochtbestendig")))
                .andExpect(jsonPath("$[2].environment", is("Recycleerbaar")))
                .andExpect(jsonPath("$[2].price", is(75.00)))
                .andExpect(jsonPath("$[2].size", is("160cmx80cm")))
                .andExpect(jsonPath("$[3].name", is("Alexa desk")))
                .andExpect(jsonPath("$[3].category", is("Bureau")))
                .andExpect(jsonPath("$[3].description", is("omschrijving")))
                .andExpect(jsonPath("$[3].image", is("afbeelding")))
                .andExpect(jsonPath("$[3].articleNumber", is("456integration")))
                .andExpect(jsonPath("$[3].delivery", is(true)))
                .andExpect(jsonPath("$[3].material", is("Hout")))
                .andExpect(jsonPath("$[3].maintenance", is("Vochtbestendig")))
                .andExpect(jsonPath("$[3].environment", is("Recycleerbaar")))
                .andExpect(jsonPath("$[3].price", is(99.99)))
                .andExpect(jsonPath("$[3].size", is("150cmx80cm")));
    }

    @Test
    public void givenProduct_whenGetProductsByCategory_thenReturnJsonProducts() throws Exception {

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product3);

        mockMvc.perform(get("/products/category/{category}", "Bureau"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].name", is("Linmon desk")))
                .andExpect(jsonPath("$[0].category", is("Bureau")))
                .andExpect(jsonPath("$[0].description", is("omschrijving")))
                .andExpect(jsonPath("$[0].image", is("afbeelding")))
                .andExpect(jsonPath("$[0].articleNumber", is("integration456")))
                .andExpect(jsonPath("$[0].delivery", is(true)))
                .andExpect(jsonPath("$[0].material", is("Hout")))
                .andExpect(jsonPath("$[0].maintenance", is("Vochtbestendig")))
                .andExpect(jsonPath("$[0].environment", is("Recycleerbaar")))
                .andExpect(jsonPath("$[0].price", is(75.00)))
                .andExpect(jsonPath("$[0].size", is("140cmx80cm")))
                .andExpect(jsonPath("$[1].name", is("Thomas desk")))
                .andExpect(jsonPath("$[1].category", is("Bureau")))
                .andExpect(jsonPath("$[1].description", is("omschrijving")))
                .andExpect(jsonPath("$[1].image", is("afbeelding")))
                .andExpect(jsonPath("$[1].articleNumber", is("123integration")))
                .andExpect(jsonPath("$[1].delivery", is(true)))
                .andExpect(jsonPath("$[1].material", is("Karton")))
                .andExpect(jsonPath("$[1].maintenance", is("Vochtbestendig")))
                .andExpect(jsonPath("$[1].environment", is("Recycleerbaar")))
                .andExpect(jsonPath("$[1].price", is(75.00)))
                .andExpect(jsonPath("$[1].size", is("160cmx80cm")))
                .andExpect(jsonPath("$[2].name", is("Alexa desk")))
                .andExpect(jsonPath("$[2].category", is("Bureau")))
                .andExpect(jsonPath("$[2].description", is("omschrijving")))
                .andExpect(jsonPath("$[2].image", is("afbeelding")))
                .andExpect(jsonPath("$[2].articleNumber", is("456integration")))
                .andExpect(jsonPath("$[2].delivery", is(true)))
                .andExpect(jsonPath("$[2].material", is("Hout")))
                .andExpect(jsonPath("$[2].maintenance", is("Vochtbestendig")))
                .andExpect(jsonPath("$[2].environment", is("Recycleerbaar")))
                .andExpect(jsonPath("$[2].price", is(99.99)))
                .andExpect(jsonPath("$[2].size", is("150cmx80cm")));
    }

    @Test
    public void whenPostProduct_thenReturnJsonProduct() throws Exception {

        Product product3 = new Product("Alexa desk", "Bureau", "omschrijving", "afbeelding", "11integration11", true, "Hout", "Vochtbestendig", "Recycleerbaar", 99.99, "150cmx80cm");

        mockMvc.perform(post("/product")
                .content(mapper.writeValueAsString(product3))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Alexa desk")))
                .andExpect(jsonPath("$.category", is("Bureau")))
                .andExpect(jsonPath("$.description", is("omschrijving")))
                .andExpect(jsonPath("$.image", is("afbeelding")))
                .andExpect(jsonPath("$.articleNumber", is("11integration11")))
                .andExpect(jsonPath("$.delivery", is(true)))
                .andExpect(jsonPath("$.material", is("Hout")))
                .andExpect(jsonPath("$.maintenance", is("Vochtbestendig")))
                .andExpect(jsonPath("$.environment", is("Recycleerbaar")))
                .andExpect(jsonPath("$.price", is(99.99)))
                .andExpect(jsonPath("$.size", is("150cmx80cm")));
    }

    @Test
    public void givenProduct_whenPutProductByArticleNumber_thenReturnJsonReview() throws Exception {

        Product updatedProduct = new Product("Linmon chair", "Bureaustoel", "omschrijving", "afbeelding", "integration123", true, "Leer", "Spray", "Recycleerbaar", 200.00, "130cm");

        mockMvc.perform(put("/product/{articleNumber}", product1.getArticleNumber())
                .content(mapper.writeValueAsString(updatedProduct))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Linmon chair")))
                .andExpect(jsonPath("$.category", is("Bureaustoel")))
                .andExpect(jsonPath("$.description", is("omschrijving")))
                .andExpect(jsonPath("$.image", is("afbeelding")))
                .andExpect(jsonPath("$.articleNumber", is("integration123")))
                .andExpect(jsonPath("$.delivery", is(true)))
                .andExpect(jsonPath("$.material", is("Leer")))
                .andExpect(jsonPath("$.maintenance", is("Spray")))
                .andExpect(jsonPath("$.environment", is("Recycleerbaar")))
                .andExpect(jsonPath("$.price", is(200.00)))
                .andExpect(jsonPath("$.size", is("130cm")));
    }

    @Test
    public void givenProduct_whenDeleteProductByArticleNumber_thenStatusOk() throws Exception {

        mockMvc.perform(delete("/product/{articleNumber}", productToBeDeleted.getArticleNumber())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenNoProduct_whenDeleteProductByArticleNumber_thenStatusNotFound() throws Exception {

        mockMvc.perform(delete("/product/{articleNumber}", "badArticleNumber")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
