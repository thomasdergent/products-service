package com.example.productsservice;

import com.example.productsservice.model.Product;
import com.example.productsservice.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductsControllerUnitTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository productrepository;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void givenProduct_whenGetProductByArticleNumber_thenReturnJsonProduct() throws Exception {

        Product product1 = new Product("Linmon chair", "Bureaustoel", "omschrijving", "afbeelding", "unitTest123", true, "Leer", "Spray", "Recycleerbaar", 200.00, "130cm");

        given(productrepository.findProductsByArticleNumber("unitTest123")).willReturn(product1);

        mockMvc.perform(get("/product/{articleNumber}",  "unitTest123"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Linmon chair")))
                .andExpect(jsonPath("$.category", is("Bureaustoel")))
                .andExpect(jsonPath("$.description", is("omschrijving")))
                .andExpect(jsonPath("$.image", is("afbeelding")))
                .andExpect(jsonPath("$.articleNumber", is("unitTest123")))
                .andExpect(jsonPath("$.delivery", is(true)))
                .andExpect(jsonPath("$.material", is("Leer")))
                .andExpect(jsonPath("$.maintenance", is("Spray")))
                .andExpect(jsonPath("$.environment", is("Recycleerbaar")))
                .andExpect(jsonPath("$.price", is(200.00)))
                .andExpect(jsonPath("$.size", is("130cm")));
    }

    @Test
    public void givenProduct_whenGetProducts_thenReturnJsonProducts() throws Exception {

        Product product1 = new Product("Linmon chair", "Bureaustoel", "omschrijving", "afbeelding", "unitTest123", true, "Leer", "Spray", "Recycleerbaar", 200.00, "130cm");
        Product product2 = new Product("Linmon desk", "Bureau", "omschrijving", "afbeelding", "unitTest456", true, "Hout", "Vochtbestendig", "Recycleerbaar", 75.00, "140cmx80cm");

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);

        given(productrepository.findAll()).willReturn(productList);

        mockMvc.perform(get("/products"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Linmon chair")))
                .andExpect(jsonPath("$[0].category", is("Bureaustoel")))
                .andExpect(jsonPath("$[0].description", is("omschrijving")))
                .andExpect(jsonPath("$[0].image", is("afbeelding")))
                .andExpect(jsonPath("$[0].articleNumber", is("unitTest123")))
                .andExpect(jsonPath("$[0].delivery", is(true)))
                .andExpect(jsonPath("$[0].material", is("Leer")))
                .andExpect(jsonPath("$[0].maintenance", is("Spray")))
                .andExpect(jsonPath("$[0].environment", is("Recycleerbaar")))
                .andExpect(jsonPath("$[0].price", is(200.00)))
                .andExpect(jsonPath("$[0].size", is("130cm")))
                .andExpect(jsonPath("$[1].name", is("Linmon desk")))
                .andExpect(jsonPath("$[1].category", is("Bureau")))
                .andExpect(jsonPath("$[1].description", is("omschrijving")))
                .andExpect(jsonPath("$[1].image", is("afbeelding")))
                .andExpect(jsonPath("$[1].articleNumber", is("unitTest456")))
                .andExpect(jsonPath("$[1].delivery", is(true)))
                .andExpect(jsonPath("$[1].material", is("Hout")))
                .andExpect(jsonPath("$[1].maintenance", is("Vochtbestendig")))
                .andExpect(jsonPath("$[1].environment", is("Recycleerbaar")))
                .andExpect(jsonPath("$[1].price", is(75.00)))
                .andExpect(jsonPath("$[1].size", is("140cmx80cm")));
    }

    @Test
    public void givenProduct_whenGetProductsByCategory_thenReturnJsonProducts() throws Exception {

        Product product1 = new Product("Linmon chair", "Bureaustoel",  "omschrijving", "afbeelding", "unitTest123", true, "Leer", "Spray", "Recycleerbaar", 200.00, "130cm");
        Product product2 = new Product("Alexa chair", "Bureaustoel","omschrijving", "afbeelding", "unitTest456", true, "Stof", "Spray", "Recycleerbaar", 75.00, "140cm");

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);

        given(productrepository.findProductsByCategory("Bureaustoel")).willReturn(productList);

        mockMvc.perform(get("/products/category/{category}", "Bureaustoel"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Linmon chair")))
                .andExpect(jsonPath("$[0].category", is("Bureaustoel")))
                .andExpect(jsonPath("$[0].description", is("omschrijving")))
                .andExpect(jsonPath("$[0].image", is("afbeelding")))
                .andExpect(jsonPath("$[0].articleNumber", is("unitTest123")))
                .andExpect(jsonPath("$[0].delivery", is(true)))
                .andExpect(jsonPath("$[0].material", is("Leer")))
                .andExpect(jsonPath("$[0].maintenance", is("Spray")))
                .andExpect(jsonPath("$[0].environment", is("Recycleerbaar")))
                .andExpect(jsonPath("$[0].price", is(200.00)))
                .andExpect(jsonPath("$[0].size", is("130cm")))
                .andExpect(jsonPath("$[1].name", is("Alexa chair")))
                .andExpect(jsonPath("$[1].category", is("Bureaustoel")))
                .andExpect(jsonPath("$[1].description", is("omschrijving")))
                .andExpect(jsonPath("$[1].image", is("afbeelding")))
                .andExpect(jsonPath("$[1].articleNumber", is("unitTest456")))
                .andExpect(jsonPath("$[1].delivery", is(true)))
                .andExpect(jsonPath("$[1].material", is("Stof")))
                .andExpect(jsonPath("$[1].maintenance", is("Spray")))
                .andExpect(jsonPath("$[1].environment", is("Recycleerbaar")))
                .andExpect(jsonPath("$[1].price", is(75.00)))
                .andExpect(jsonPath("$[1].size", is("140cm")));
    }

    @Test
    public void whenPostProduct_thenReturnJsonProduct() throws Exception {

        Product product3 = new Product("Linmon chair", "Bureaustoel","omschrijving", "afbeelding", "123unitTest", true, "Leer", "Spray", "Recycleerbaar", 200.00, "130cm");

        mockMvc.perform(post("/product")
                .content(mapper.writeValueAsString(product3))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Linmon chair")))
                .andExpect(jsonPath("$.category", is("Bureaustoel")))
                .andExpect(jsonPath("$.description", is("omschrijving")))
                .andExpect(jsonPath("$.image", is("afbeelding")))
                .andExpect(jsonPath("$.articleNumber", is("123unitTest")))
                .andExpect(jsonPath("$.delivery", is(true)))
                .andExpect(jsonPath("$.material", is("Leer")))
                .andExpect(jsonPath("$.maintenance", is("Spray")))
                .andExpect(jsonPath("$.environment", is("Recycleerbaar")))
                .andExpect(jsonPath("$.price", is(200.00)))
                .andExpect(jsonPath("$.size", is("130cm")));
    }

    @Test
    public void givenProduct_whenPutProductByArticleNumber_thenReturnJsonProduct() throws Exception {
        Product product1 = new Product("Linmon chair", "Bureaustoel","omschrijving", "afbeelding", "unitTest123", true, "Leer", "Spray", "Recycleerbaar", 200.00, "130cm");

        given(productrepository.findProductsByArticleNumber("unitTest123")).willReturn(product1);

        Product updatedProduct = new Product("Linmon chair", "Bureaustoel","omschrijving", "afbeelding", "unitTest123", true, "Leer", "Spray", "Recycleerbaar", 200.00, "130cm");

        mockMvc.perform(put("/product/" + product1.getArticleNumber())
                .content(mapper.writeValueAsString(updatedProduct))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Linmon chair")))
                .andExpect(jsonPath("$.category", is("Bureaustoel")))
                .andExpect(jsonPath("$.description", is("omschrijving")))
                .andExpect(jsonPath("$.image", is("afbeelding")))
                .andExpect(jsonPath("$.articleNumber", is("unitTest123")))
                .andExpect(jsonPath("$.delivery", is(true)))
                .andExpect(jsonPath("$.material", is("Leer")))
                .andExpect(jsonPath("$.maintenance", is("Spray")))
                .andExpect(jsonPath("$.environment", is("Recycleerbaar")))
                .andExpect(jsonPath("$.price", is(200.00)))
                .andExpect(jsonPath("$.size", is("130cm")));
    }

    @Test
    public void givenProduct_whenDeleteProductByArticleNumber_thenStatusOk() throws Exception {
        Product productToBeDeleted = new Product("Linmon chair", "Bureaustoel","omschrijving", "afbeelding", "delete", true, "Leer", "Spray", "Recycleerbaar", 200.00, "130cm");


        given(productrepository.findProductsByArticleNumber("delete")).willReturn(productToBeDeleted);

        mockMvc.perform(delete("/product/" + productToBeDeleted.getArticleNumber())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenNoReview_whenDeleteProductByArticleNumber_thenStatusNotFound() throws Exception {
        given(productrepository.findProductsByArticleNumber("badArticleNumber")).willReturn(null);

        mockMvc.perform(delete("/product/badArticleNumber")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}