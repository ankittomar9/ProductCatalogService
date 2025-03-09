package com.company.productcatalogservice.repos;

import com.company.productcatalogservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

       Optional<Product> findProductById(Long id);

       Product save(Product p);

       List<Product> findAll();
}
