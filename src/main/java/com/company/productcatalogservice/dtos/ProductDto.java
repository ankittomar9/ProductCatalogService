package com.company.productcatalogservice.dtos;

import com.company.productcatalogservice.models.Category;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter

public class ProductDto {  //similar to product in base model class
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private Double price;
    private CategoryDto category;

}