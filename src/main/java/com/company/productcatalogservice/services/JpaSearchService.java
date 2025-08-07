package com.company.productcatalogservice.services;

import com.company.productcatalogservice.dtos.SortParam;
import com.company.productcatalogservice.dtos.SortType;
import com.company.productcatalogservice.models.Product;
import com.company.productcatalogservice.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
@Service
public class JpaSearchService implements ISearchService {

    @Autowired
    private ProductRepo productRepo;

    @Override
    public Page<Product> searchProducts(String searchQuery, int pageSize, int pageNumber, List<SortParam> sortParams) {

        if(sortParams.isEmpty()) {
            return productRepo.findProductsByName(searchQuery,
                    PageRequest.of(pageNumber, pageSize));
        }

        Sort sort = null;
        if(sortParams.get(0).getSortType().equals(SortType.ASC)) {
            sort = Sort.by(sortParams.get(0).getSortCriteria());
        }else {
            sort = Sort.by(sortParams.get(0).getSortCriteria()).descending();
        }


        for(int i=1;i<sortParams.size();i++) {
            if(sortParams.get(i).getSortType().equals(SortType.ASC)) {
                sort = sort.and(Sort.by(sortParams.get(i).getSortCriteria()));
            }else {
                sort = sort.and(Sort.by(sortParams.get(i).getSortCriteria()).descending());
            }
        }

//        Sort sort = Sort.by("price").and(Sort.by("id").descending());
//
//        Page<Product> products = productRepo.findProductsByName(searchQuery,
//                PageRequest.of(pageNumber, pageSize,sort));
//        return products;

        return productRepo.findProductsByName(searchQuery,
                PageRequest.of(pageNumber, pageSize,sort));
    }
}