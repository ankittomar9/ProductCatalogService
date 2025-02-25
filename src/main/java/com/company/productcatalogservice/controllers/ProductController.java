package com.company.productcatalogservice.controllers;

import com.company.productcatalogservice.dtos.CategoryDto;
import com.company.productcatalogservice.dtos.FakeStoreProductDto;
import com.company.productcatalogservice.dtos.ProductDto;
import com.company.productcatalogservice.models.Category;
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
    @GetMapping("{productId}")
    public ProductDto findProductById(@PathVariable Long productId){
        Product product =productService.getProductById(productId);
        return from(product);
    }

    private ProductDto from(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImageUrl(product.getImageUrl());
       // Category category  = new Category();
        if(product.getCategory()!=null){
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setName(product.getCategory().getName());
            categoryDto.setId(product.getCategory().getId());
            categoryDto.setDescription(product.getCategory().getDescription());
            productDto.setCategory(categoryDto);
        }

        return productDto;
    }
    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product){
        return product;
    }


}
