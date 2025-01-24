package com.project.Product_Catalogue.repository;

import com.project.Product_Catalogue.entity.Product;
import org.openapitools.api.ProductApi;
import org.openapitools.model.ProductSearchRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByName(String name);

}
