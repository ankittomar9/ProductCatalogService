package com.company.productcatalogservice.services;

import com.company.productcatalogservice.dtos.FakeStoreProductDto;
import com.company.productcatalogservice.models.Category;
import com.company.productcatalogservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService  implements IProductService{

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

   /* public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }*/

    public Product getProductById(Long productId) {
        RestTemplate restTemplate = restTemplateBuilder.build();  // object mapper jackson
       ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity
               = restTemplate.getForEntity("http://fakestoreapi.com/products/{productId}", FakeStoreProductDto.class, productId);
        if(fakeStoreProductDtoResponseEntity.getStatusCode().equals(HttpStatusCode.valueOf(200)) && fakeStoreProductDtoResponseEntity.getBody() != null) {
            return from(fakeStoreProductDtoResponseEntity.getBody());
        }
       return null;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
      RestTemplate restTemplate = restTemplateBuilder.build();
      ResponseEntity<FakeStoreProductDto[]> listResponseEntity=

              restTemplate.getForEntity("http://fakestoreapi.com/products",
                      FakeStoreProductDto[].class); // Study generics type always return simple primitive data type like array
   //put  if else here if required
    for(FakeStoreProductDto fakeStoreProductDto : listResponseEntity.getBody()) {
        products.add(from(fakeStoreProductDto));
    }
    return products;
    }


    private Product from(FakeStoreProductDto fakeStoreProductDto){
    Product product = new Product();
    product.setId(fakeStoreProductDto.getId());
    product.setName(fakeStoreProductDto.getTitle());
    product.setDescription(fakeStoreProductDto.getDescription());
    product.setPrice(fakeStoreProductDto.getPrice());
    product.setImageUrl(fakeStoreProductDto.getImage());
        Category category  = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }

}
