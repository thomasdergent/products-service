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

    private Product product1Store1 = new Product("IKEA Hasselt", "Linmon chair", "Bureaustoel", "integration123", true, "Leer", "Spray", "Recycleerbaar", 500, 200.00, "130cm");
    private Product product2Store1 = new Product("IKEA Hasselt", "Linmon desk", "Bureau", "integration456", true, "Hout", "Vochtbestendig", "Recycleerbaar", 600, 75.00, "140cmx80cm");
    private Product product3Store2 = new Product("IKEA Wilrijk", "Linmon desk", "Bureau", "123integration", true, "Hout", "Vochtbestendig", "Recycleerbaar", 600, 75.00, "140cmx80cm");

    private Product productToBeDeleted = new Product("IKEA Wilrijk", "Alexa desk", "Bureau", "456integration", true, "Hout", "Vochtbestendig", "Recycleerbaar", 600, 99.99, "150cmx80cm");

    @BeforeEach
    public void beforeAllTests() {
        productRepository.deleteAll();
        productRepository.save(product1Store1);
        productRepository.save(product2Store1);
        productRepository.save(product3Store2);
        productRepository.save(productToBeDeleted);
    }

    @AfterEach
    public void afterAllTests() {
        //Watch out with deleteAll() methods when you have other data in the test database!
        productRepository.deleteAll();
    }

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void givenProduct_whenGetProductByStoreNameAndArticleNumber_thenReturnJsonProduct() throws Exception {

        mockMvc.perform(get("/store/{storeName}/article/{articleNumber}", product1Store1.getStoreName(), product1Store1.getArticleNumber()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.storeName", is("IKEA Hasselt")))
                .andExpect(jsonPath("$.name", is("Linmon chair")))
                .andExpect(jsonPath("$.category", is("Bureaustoel")))
                .andExpect(jsonPath("$.articleNumber", is("integration123")))
                .andExpect(jsonPath("$.delivery", is(true)))
                .andExpect(jsonPath("$.material", is("Leer")))
                .andExpect(jsonPath("$.maintenance", is("Spray")))
                .andExpect(jsonPath("$.environment", is("Recycleerbaar")))
                .andExpect(jsonPath("$.stock", is(500)))
                .andExpect(jsonPath("$.price", is(200.00)))
                .andExpect(jsonPath("$.size", is("130cm")));
    }

    @Test
    public void givenProduct_whenGetProductsByStorName_thenReturnJsonProducts() throws Exception {

        List<Product> productList = new ArrayList<>();
        productList.add(product1Store1);
        productList.add(product2Store1);

        mockMvc.perform(get("/products/IKEA Hasselt"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Linmon chair")))
                .andExpect(jsonPath("$[0].category", is("Bureaustoel")))
                .andExpect(jsonPath("$[0].articleNumber", is("integration123")))
                .andExpect(jsonPath("$[0].delivery", is(true)))
                .andExpect(jsonPath("$[0].material", is("Leer")))
                .andExpect(jsonPath("$[0].maintenance", is("Spray")))
                .andExpect(jsonPath("$[0].environment", is("Recycleerbaar")))
                .andExpect(jsonPath("$[0].stock", is(500)))
                .andExpect(jsonPath("$[0].price", is(200.00)))
                .andExpect(jsonPath("$[0].size", is("130cm")))
                .andExpect(jsonPath("$[1].name", is("Linmon desk")))
                .andExpect(jsonPath("$[1].category", is("Bureau")))
                .andExpect(jsonPath("$[1].articleNumber", is("integration456")))
                .andExpect(jsonPath("$[1].delivery", is(true)))
                .andExpect(jsonPath("$[1].material", is("Hout")))
                .andExpect(jsonPath("$[1].maintenance", is("Vochtbestendig")))
                .andExpect(jsonPath("$[1].environment", is("Recycleerbaar")))
                .andExpect(jsonPath("$[1].stock", is(600)))
                .andExpect(jsonPath("$[1].price", is(75.00)))
                .andExpect(jsonPath("$[1].size", is("140cmx80cm")));
    }

    @Test
    public void givenProduct_whenGetProductsByStorNameAndCategory_thenReturnJsonProducts() throws Exception {

        List<Product> productList = new ArrayList<>();
        productList.add(product1Store1);

        mockMvc.perform(get("/store/{storeName}/category/{category}", "IKEA Hasselt", "Bureaustoel"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("Linmon chair")))
                .andExpect(jsonPath("$[0].category", is("Bureaustoel")))
                .andExpect(jsonPath("$[0].articleNumber", is("integration123")))
                .andExpect(jsonPath("$[0].delivery", is(true)))
                .andExpect(jsonPath("$[0].material", is("Leer")))
                .andExpect(jsonPath("$[0].maintenance", is("Spray")))
                .andExpect(jsonPath("$[0].environment", is("Recycleerbaar")))
                .andExpect(jsonPath("$[0].stock", is(500)))
                .andExpect(jsonPath("$[0].price", is(200.00)))
                .andExpect(jsonPath("$[0].size", is("130cm")));
    }

    @Test
    public void whenPostProduct_thenReturnJsonProduct() throws Exception {

        Product product3Store1 = new Product("IKEA Hasselt", "Alexa desk", "Bureau", "11integration11", true, "Hout", "Vochtbestendig", "Recycleerbaar", 600, 99.99, "150cmx80cm");

        mockMvc.perform(post("/product")
                .content(mapper.writeValueAsString(product3Store1))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.storeName", is("IKEA Hasselt")))
                .andExpect(jsonPath("$.name", is("Alexa desk")))
                .andExpect(jsonPath("$.category", is("Bureau")))
                .andExpect(jsonPath("$.articleNumber", is("11integration11")))
                .andExpect(jsonPath("$.delivery", is(true)))
                .andExpect(jsonPath("$.material", is("Hout")))
                .andExpect(jsonPath("$.maintenance", is("Vochtbestendig")))
                .andExpect(jsonPath("$.environment", is("Recycleerbaar")))
                .andExpect(jsonPath("$.stock", is(600)))
                .andExpect(jsonPath("$.price", is(99.99)))
                .andExpect(jsonPath("$.size", is("150cmx80cm")));
    }

    @Test
    public void givenProduct_whenPutProductByStoreNameAndArticleNumber_thenReturnJsonReview() throws Exception {

        Product updatedProduct = new Product("IKEA Hasselt", "Linmon chair", "Bureaustoel", "integration123", true, "Leer", "Spray", "Recycleerbaar", 100, 200.00, "130cm");

        mockMvc.perform(put("/store/{storeName}/article/{articleNumber}", product1Store1.getStoreName(), product1Store1.getArticleNumber())
                .content(mapper.writeValueAsString(updatedProduct))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.storeName", is("IKEA Hasselt")))
                .andExpect(jsonPath("$.name", is("Linmon chair")))
                .andExpect(jsonPath("$.category", is("Bureaustoel")))
                .andExpect(jsonPath("$.articleNumber", is("integration123")))
                .andExpect(jsonPath("$.delivery", is(true)))
                .andExpect(jsonPath("$.material", is("Leer")))
                .andExpect(jsonPath("$.maintenance", is("Spray")))
                .andExpect(jsonPath("$.environment", is("Recycleerbaar")))
                .andExpect(jsonPath("$.stock", is(100)))
                .andExpect(jsonPath("$.price", is(200.00)))
                .andExpect(jsonPath("$.size", is("130cm")));
    }

    @Test
    public void givenReview_whenDeleteReview_thenStatusOk() throws Exception {

        mockMvc.perform(delete("/product/store/{storeName}/article/{articleNumber}", productToBeDeleted.getStoreName(), productToBeDeleted.getArticleNumber())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenNoProduct_whenDeleteProductByStoreNameAndArticleNumber_thenStatusNotFound() throws Exception {

        mockMvc.perform(delete("/product/store/{storeName}/article/{articleNumber}", "badStore", "badArticleNumber")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
