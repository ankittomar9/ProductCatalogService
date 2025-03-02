package com.company.productcatalogservice.services;

import com.company.productcatalogservice.clients.FakeStoreApiClient;
import com.company.productcatalogservice.dtos.FakeStoreProductDto;
import com.company.productcatalogservice.models.Category;
import com.company.productcatalogservice.models.Product;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService  implements IProductService{

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    private FakeStoreApiClient fakeStoreApiClient;

   /* public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }*/

    public Product getProductById(Long productId) {
        FakeStoreProductDto fakeStoreProductDto = fakeStoreApiClient.getProductById(productId);
        if(fakeStoreApiClient != null) {
            return from(fakeStoreProductDto);
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

    @Override
    public Product replaceProduct(Long productId, Product request) {
//        RestTemplate restTemplate = restTemplateBuilder.build();  Not required since below custom function can be used for all
      FakeStoreProductDto fakeStoreProductDtoRequest = from(request);
       FakeStoreProductDto response= requestForEntity("http://fakestoreapi.com/products/{productId}",HttpMethod.PUT,fakeStoreProductDtoRequest,FakeStoreProductDto.class,productId).getBody();
    return from(response);
    }


//Engineering custom method for all methods using http methods
    private <T> ResponseEntity<T> requestForEntity(String url, HttpMethod httpMethod, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }
    private FakeStoreProductDto from(Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setTitle(product.getName());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImageUrl());
        if(product.getCategory() != null) {
            fakeStoreProductDto.setCategory(product.getCategory().getName());
        }
        return fakeStoreProductDto;
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
