package com.project.Product_Catalogue.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.openapitools.model.PageResponseDTO;
import org.openapitools.model.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.Product_Catalogue.entity.Product;
import com.project.Product_Catalogue.repository.ProductRepository;

    @Service
    public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        // Convert ProductDTO to Product entity
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategoryIds(productDTO.getCategoryIds());
        product.setCreatedDate(OffsetDateTime.now());
        product.setUpdatedDate(OffsetDateTime.now());

        // Save the entity
        Product savedProduct = productRepository.save(product);

        // Convert back to ProductDTO
        return convertToDTO(savedProduct);
    }

    public ProductDTO getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(this::convertToDTO).orElse(null);
    }


    public PageResponseDTO getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findAll(pageable);
        List<ProductDTO> productDTOs = productPage.getContent().stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
        PageResponseDTO pageResponseDTO = new PageResponseDTO();
        pageResponseDTO.setContent(productDTOs);
        pageResponseDTO.setPageNumber(productPage.getNumber());
        pageResponseDTO.setPageSize(productPage.getSize());
        pageResponseDTO.setTotalElements(productPage.getTotalElements());
        pageResponseDTO.setTotalPages(productPage.getTotalPages());
        return pageResponseDTO;

    }


    public boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        if (productRepository.existsById(id)) {
            Product product = new Product();
            product.setId(id);
            product.setName(productDTO.getName());
            product.setDescription(productDTO.getDescription());
            product.setPrice(productDTO.getPrice());
            product.setCategoryIds(productDTO.getCategoryIds());
            product.setUpdatedDate(OffsetDateTime.now());

            Product updatedProduct = productRepository.save(product);
            return convertToDTO(updatedProduct);
        }
        return null;
    }

    // Helper method to convert Product to ProductDTO
    private ProductDTO convertToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setCategoryIds(product.getCategoryIds());
        productDTO.setCreatedDate(product.getCreatedDate());
        productDTO.setUpdatedDate(product.getUpdatedDate());
        return productDTO;
    }
        public boolean isProductExists(String name) {
            return productRepository.existsByName(name);
        }

        public boolean isProductExistsById(Long id) {
            return productRepository.existsById(id);
        }
}