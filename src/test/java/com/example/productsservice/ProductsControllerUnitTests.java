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
    public void whenGetProductByStoreNameAndArticleNumber_thenReturnJsonProduct() throws Exception {

        Product productStore1Category1 = new Product("IKEA Hasselt", "Linmon chair", "Bureaustoel", "omschrijving", "afbeelding", "unitTest123", true, "Leer", "Spray", "Recycleerbaar", 500, 200.00, "130cm");

        given(productrepository.findProductsByStoreNameAndArticleNumber("IKEA Hasselt", "unitTest123")).willReturn(productStore1Category1);

        mockMvc.perform(get("/store/{storeName}/article/{articleNumber}", "IKEA Hasselt", "unitTest123"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.storeName", is("IKEA Hasselt")))
                .andExpect(jsonPath("$.name", is("Linmon chair")))
                .andExpect(jsonPath("$.category", is("Bureaustoel")))
                .andExpect(jsonPath("$.description", is("omschrijving")))
                .andExpect(jsonPath("$.image", is("afbeelding")))
                .andExpect(jsonPath("$.articleNumber", is("unitTest123")))
                .andExpect(jsonPath("$.delivery", is(true)))
                .andExpect(jsonPath("$.material", is("Leer")))
                .andExpect(jsonPath("$.maintenance", is("Spray")))
                .andExpect(jsonPath("$.environment", is("Recycleerbaar")))
                .andExpect(jsonPath("$.stock", is(500)))
                .andExpect(jsonPath("$.price", is(200.00)))
                .andExpect(jsonPath("$.size", is("130cm")));
    }

    @Test
    public void whenGetProductByStoreName_thenReturnJsonProducts() throws Exception {

        Product productStore1Category1 = new Product("IKEA Hasselt", "Linmon chair", "Bureaustoel", "omschrijving", "afbeelding", "unitTest123", true, "Leer", "Spray", "Recycleerbaar", 500, 200.00, "130cm");
        Product productStore1Category2 = new Product("IKEA Hasselt", "Linmon desk", "Bureau", "omschrijving", "afbeelding", "unitTest456", true, "Hout", "Vochtbestendig", "Recycleerbaar", 600, 75.00, "140cmx80cm");

        List<Product> productList = new ArrayList<>();
        productList.add(productStore1Category1);
        productList.add(productStore1Category2);

        given(productrepository.findProductsByStoreName("IKEA Hasselt")).willReturn(productList);

        mockMvc.perform(get("/products/{storeName}", "IKEA Hasselt"))
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
                .andExpect(jsonPath("$[0].stock", is(500)))
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
                .andExpect(jsonPath("$[1].stock", is(600)))
                .andExpect(jsonPath("$[1].price", is(75.00)))
                .andExpect(jsonPath("$[1].size", is("140cmx80cm")));
    }

    @Test
    public void whenGetProductsByStoreNameAndCategory_thenReturnJsonProducts() throws Exception {

        Product productStore1Category1 = new Product("IKEA Hasselt", "Linmon chair", "Bureaustoel",  "omschrijving", "afbeelding", "unitTest123", true, "Leer", "Spray", "Recycleerbaar", 500, 200.00, "130cm");
        Product productStore1Category2 = new Product("IKEA Hasselt", "Alexa chair", "Bureaustoel","omschrijving", "afbeelding", "unitTest456", true, "Stof", "Spray", "Recycleerbaar", 600, 75.00, "140cm");

        List<Product> productList = new ArrayList<>();
        productList.add(productStore1Category1);
        productList.add(productStore1Category2);

        given(productrepository.findProductByStoreNameAndAndCategory("IKEA Hasselt", "Bureaustoel")).willReturn(productList);

        mockMvc.perform(get("/store/{storeName}/category/{category}", "IKEA Hasselt", "Bureaustoel"))
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
                .andExpect(jsonPath("$[0].stock", is(500)))
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
                .andExpect(jsonPath("$[1].stock", is(600)))
                .andExpect(jsonPath("$[1].price", is(75.00)))
                .andExpect(jsonPath("$[1].size", is("140cm")));
    }

    @Test
    public void whenPostProduct_thenReturnJsonProduct() throws Exception {

        Product productStore1Category3 = new Product("IKEA Hasselt", "Linmon chair", "Bureaustoel","omschrijving", "afbeelding", "123unitTest", true, "Leer", "Spray", "Recycleerbaar", 500, 200.00, "130cm");

        mockMvc.perform(post("/product")
                .content(mapper.writeValueAsString(productStore1Category3))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.storeName", is("IKEA Hasselt")))
                .andExpect(jsonPath("$.name", is("Linmon chair")))
                .andExpect(jsonPath("$.category", is("Bureaustoel")))
                .andExpect(jsonPath("$.description", is("omschrijving")))
                .andExpect(jsonPath("$.image", is("afbeelding")))
                .andExpect(jsonPath("$.articleNumber", is("123unitTest")))
                .andExpect(jsonPath("$.delivery", is(true)))
                .andExpect(jsonPath("$.material", is("Leer")))
                .andExpect(jsonPath("$.maintenance", is("Spray")))
                .andExpect(jsonPath("$.environment", is("Recycleerbaar")))
                .andExpect(jsonPath("$.stock", is(500)))
                .andExpect(jsonPath("$.price", is(200.00)))
                .andExpect(jsonPath("$.size", is("130cm")));
    }

    @Test
    public void givenProductForStoreAndArticleNumber_whenPutProductForStoreAndArticleNumber_thenReturnJsonProduct() throws Exception {
        Product productStore1Category1 = new Product("IKEA Hasselt", "Linmon chair", "Bureaustoel","omschrijving", "afbeelding", "unitTest123", true, "Leer", "Spray", "Recycleerbaar", 500, 200.00, "130cm");

        given(productrepository.findProductsByStoreNameAndArticleNumber("IKEA Hasselt", "unitTest123")).willReturn(productStore1Category1);

        Product updatedProduct = new Product("IKEA Hasselt", "Linmon chair", "Bureaustoel","omschrijving", "afbeelding", "unitTest123", true, "Leer", "Spray", "Recycleerbaar", 100, 200.00, "130cm");

        mockMvc.perform(put("/store/" + productStore1Category1.getStoreName() + "/article/" + productStore1Category1.getArticleNumber())
                .content(mapper.writeValueAsString(updatedProduct))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.storeName", is("IKEA Hasselt")))
                .andExpect(jsonPath("$.name", is("Linmon chair")))
                .andExpect(jsonPath("$.category", is("Bureaustoel")))
                .andExpect(jsonPath("$.description", is("omschrijving")))
                .andExpect(jsonPath("$.image", is("afbeelding")))
                .andExpect(jsonPath("$.articleNumber", is("unitTest123")))
                .andExpect(jsonPath("$.delivery", is(true)))
                .andExpect(jsonPath("$.material", is("Leer")))
                .andExpect(jsonPath("$.maintenance", is("Spray")))
                .andExpect(jsonPath("$.environment", is("Recycleerbaar")))
                .andExpect(jsonPath("$.stock", is(100)))
                .andExpect(jsonPath("$.price", is(200.00)))
                .andExpect(jsonPath("$.size", is("130cm")));
    }

    @Test
    public void givenProductForStoreAndArticleNumber_whenDeleteProductForStoreAndArticleNumber_thenStatusOk() throws Exception {
        Product productToBeDeleted = new Product("IKEA Hasselt", "Linmon chair", "Bureaustoel","omschrijving", "afbeelding", "delete", true, "Leer", "Spray", "Recycleerbaar", 500, 200.00, "130cm");


        given(productrepository.findProductsByStoreNameAndArticleNumber("IKEA Hasselt", "delete")).willReturn(productToBeDeleted);

        mockMvc.perform(delete("/product/store/" + productToBeDeleted.getStoreName() + "/article/" + productToBeDeleted.getArticleNumber())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenNoReview_whenDeleteReview_thenStatusNotFound() throws Exception {
        given(productrepository.findProductsByStoreNameAndArticleNumber("badStore", "badArticleNumber")).willReturn(null);

        mockMvc.perform(delete("/product/store/badStore/article/badArticleNumber")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}