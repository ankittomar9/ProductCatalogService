package com.company.productcatalogservice.services;

import com.company.productcatalogservice.dtos.FakeStoreProductDto;
import com.company.productcatalogservice.models.Category;
import com.company.productcatalogservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreProductService  implements IProductService{

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

   /* public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }*/

    public Product getProductById(Long productId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
       FakeStoreProductDto fakeStoreProductDto
               = restTemplate.getForEntity("http://fakestoreapi.com/products/{productId}", FakeStoreProductDto.class, productId).getBody();
        return from(fakeStoreProductDto);
    }



    private Product from(FakeStoreProductDto fakeStoreProductDto){
    Product product = new Product();
    product.setId(fakeStoreProductDto.getId());
    product.setName(fakeStoreProductDto.getTitle());
    product.setDescription(fakeStoreProductDto.getDescription());
    product.setPrice(fakeStoreProductDto.getPrice());
    product.setImageURL(fakeStoreProductDto.getImage());
        Category category  = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }

}
