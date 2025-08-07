package com.company.productcatalogservice.services;

import com.company.productcatalogservice.models.Product;
import org.springframework.data.domain.Page;
import com.company.productcatalogservice.dtos.SortParam;
import java.util.List;

public interface ISearchService {

     Page<Product> searchProducts
             (String searchQuery, int pageSize, int pageNumber,
              List<SortParam> sortParams);
}
