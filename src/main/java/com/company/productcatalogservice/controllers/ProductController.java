package com.company.productcatalogservice.controllers;

import com.company.productcatalogservice.models.Product;
import com.company.productcatalogservice.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {


    @Autowired
    private IProductService productService;

    
    @GetMapping("/products")
    public List<Product> getAllProducts(){
        Product product = new Product();
        product.setId(2L);
        product.setName("Android");
        List<Product> products = new ArrayList<>();
        products.add(product);
        return products;
        //return null;
    }
    @GetMapping("/products/{productId}")
    public Product findProductById(@PathVariable Long productId){
        Product product = new Product();
        product.setId(productId);
        return product;
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product){
        return product;
    }


}
