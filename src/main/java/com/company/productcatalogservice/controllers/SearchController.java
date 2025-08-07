package com.company.productcatalogservice.controllers;

import com.company.productcatalogservice.dtos.SearchRequestDto;
import com.company.productcatalogservice.models.Product;
import com.company.productcatalogservice.services.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private ISearchService searchService;

    @PostMapping
    public Page<Product> searchProducts(@RequestBody SearchRequestDto searchRequestDto) {
    return searchService.searchProducts(
            searchRequestDto.getSearchQuery(),
            searchRequestDto.getPageSize(),
            searchRequestDto.getPageNumber(),
            searchRequestDto.getSortParams());
    }

}

//{
//        "query" : "chocolate",
//        "pageSize" : 10,
//        "pageNumber" : 0,
//        "sortParams" : [
//        {
//        "sortType" : "ASC",
//        "sortCriteria" : "price"
//        },
//        {
//        "sortType" : "DESC",
//        "sortCriteria" : "id"
//        }
//        ]
//}