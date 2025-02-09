package com.company.productcatalogservice.services;

import com.company.productcatalogservice.models.Product;

public interface IProductService {
     Product getProductById(Long productId);
}
