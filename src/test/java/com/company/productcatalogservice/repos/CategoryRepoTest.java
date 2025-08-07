package com.company.productcatalogservice.repos;

import com.company.productcatalogservice.models.Category;
import com.company.productcatalogservice.models.Product;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryRepoTest {

    @Autowired
        private CategoryRepo categoryRepo;

    @Test
    @Transactional
    void testFetchTypes(){
        Category category = categoryRepo.findById(212L).get();
        for(Product product : category.getProducts()){
            System.out.println(product.getName());
        }
    }
}