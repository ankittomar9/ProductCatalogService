package com.company.productcatalogservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class FakeStoreProductDto {
    private Long id;
    private String title;
    private Double price;
    private String description;
    private String Category;
    private String image;
}
