package com.project.Product_Catalogue.controller;

import com.project.Product_Catalogue.exception.CategoryNotFoundException;
import com.project.Product_Catalogue.exception.ProductNotFoundException;
import com.project.Product_Catalogue.service.CategoryServiceImpl;
import com.project.Product_Catalogue.service.InventoryServiceImpl;
import com.project.Product_Catalogue.service.ProductService;
import org.jetbrains.annotations.NotNull;
import org.openapitools.api.ProductApi;
import org.openapitools.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductController implements ProductApi {

    @Autowired
    public ProductService productService;
    public CategoryServiceImpl categoryService;
    public InventoryServiceImpl inventoryService;

    public ProductController(ProductService productService, CategoryServiceImpl categoryService, InventoryServiceImpl inventoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.inventoryService = inventoryService;
    }

    @Override
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        if (productDTO == null || productDTO.getName() == null || productDTO.getPrice()<=0) {
            ErrorDTO error = new ErrorDTO().code("400").message("Invalid product data");
            return ResponseEntity.status(400).body(null);
        }
        try{
        if (productService.isProductExists(productDTO.getName())) {
            ErrorDTO error = new ErrorDTO().code("409").message("Product already exists");
            return ResponseEntity.status(409).body(null);
        }
        ProductDTO createdProduct = productService.createProduct(productDTO);
        return ResponseEntity.status(201).body(createdProduct);
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @Override
    public ResponseEntity<ProductDTO> getProductById(@PathVariable("id") Long id) {
        if (id <= 0) {
            ErrorDTO error = new ErrorDTO().code("400").message("Invalid product ID");
            return ResponseEntity.status(400).body(null);
        }
        try {
            ProductDTO product = productService.getProductById(id);
            if (product != null) {
                return ResponseEntity.ok(product);
            } else {
                throw new ProductNotFoundException("Product not found with ID: " + id);
            }
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(404).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @Override
    public ResponseEntity<PageResponseDTO> getAllProducts(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "20") Integer size) {
        if (page < 0 || size <= 0) {
            ErrorDTO error = new ErrorDTO().code("400").message("Invalid pagination parameters");
            return ResponseEntity.status(400).body(null);
        }
        try {
            PageResponseDTO pageResponseDTO = productService.getAllProducts(page, size);
            return ResponseEntity.ok(pageResponseDTO);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @Override
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        if (id <= 0) {
            ErrorDTO error = new ErrorDTO().code("400").message("Invalid product ID");
            return ResponseEntity.status(400).body(null);
        }
        try {
            boolean isDeleted = productService.deleteProduct(id);
            if (isDeleted) {
                return ResponseEntity.noContent().build();
            } else {
                throw new ProductNotFoundException("Product not found with ID: " + id);
            }
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(404).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @Override
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable("id") Long id, @RequestBody ProductDTO productDTO) {
        if (id <= 0 || productDTO == null || productDTO.getName() == null || productDTO.getPrice() <=0) {
            ErrorDTO error = new ErrorDTO().code("400").message("Invalid product data or ID");
            return ResponseEntity.status(400).body(null);
        }
        try {
            ProductDTO updatedProduct = productService.updateProduct(id, productDTO);
            if (updatedProduct != null) {
                return ResponseEntity.ok(updatedProduct);
            } else {
                throw new ProductNotFoundException("Product not found with ID: " + id);
            }
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(404).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @Override
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        if (categoryDTO == null || categoryDTO.getName() == null) {
            ErrorDTO error = new ErrorDTO().code("400").message("Invalid category data");
            return ResponseEntity.status(400).body(null);
        }
        try {
            if (categoryService.isCategoryExists(categoryDTO.getName())) {
                ErrorDTO error = new ErrorDTO().code("409").message("Category already exists");
                return ResponseEntity.status(409).body(null);
            }
            CategoryDTO createdCategory = categoryService.createCategory(categoryDTO);
            return ResponseEntity.status(201).body(createdCategory);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @Override
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        try {
            List<CategoryDTO> categories = categoryService.getAllCategories();
            return ResponseEntity.ok(categories);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @Override
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
        if (id <= 0) {
            ErrorDTO error = new ErrorDTO().code("400").message("Invalid category ID");
            return ResponseEntity.status(400).body(null);
        }
        try {
            CategoryDTO category = categoryService.getCategoryById(id);
            if (category == null) {
                throw new CategoryNotFoundException("Category not found with ID: " + id);
            }
            return ResponseEntity.ok(category);
        } catch (CategoryNotFoundException e) {
            return ResponseEntity.status(404).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @Override
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryDTO categoryDTO) {
        if (id <= 0 || categoryDTO == null || categoryDTO.getName() == null) {
            ErrorDTO error = new ErrorDTO().code("400").message("Invalid category data or ID");
            return ResponseEntity.status(400).body(null);
        }
        try {
            CategoryDTO updatedCategory = categoryService.updateCategory(id, categoryDTO);
            if (updatedCategory == null) {
                throw new CategoryNotFoundException("Category not found with ID: " + id);
            }
            return ResponseEntity.ok(updatedCategory);
        } catch (CategoryNotFoundException e) {
            return ResponseEntity.status(404).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @Override
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        if (id <= 0) {
            ErrorDTO error = new ErrorDTO().code("400").message("Invalid category ID");
            return ResponseEntity.status(400).body(null);
        }
        try {
            categoryService.deleteCategory(id);
            return ResponseEntity.noContent().build();
        } catch (CategoryNotFoundException e) {
            return ResponseEntity.status(404).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @Override
    public ResponseEntity<InventoryDTO> updateInventory(@Valid @RequestBody InventoryDTO inventoryDTO) {
        if (inventoryDTO == null || inventoryDTO.getProductId() <=0 || inventoryDTO.getQuantity() <=0) {
            ErrorDTO error = new ErrorDTO().code("400").message("Invalid inventory data");
            return ResponseEntity.status(400).body(null);
        }
        try {
            // Check if the product exists
            if (!productService.isProductExistsById(inventoryDTO.getProductId())) {
                ErrorDTO error = new ErrorDTO().code("404").message("Product not found with ID: " + inventoryDTO.getProductId());
                return ResponseEntity.status(404).body(null);
            }
            InventoryDTO updatedInventory = inventoryService.updateInventory(inventoryDTO);
            return ResponseEntity.ok(updatedInventory);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @Override
    public ResponseEntity<List<InventoryDTO>> getAllInventory() {
        try {
            List<InventoryDTO> inventory = inventoryService.getAllInventory();
            return ResponseEntity.ok(inventory);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @Override
    public ResponseEntity<List<InventoryDTO>> getLowStockProducts(@RequestParam(defaultValue = "10") Integer threshold) {
        if (threshold < 0) {
            ErrorDTO error = new ErrorDTO().code("400").message("Invalid threshold value");
            return ResponseEntity.status(400).body(null);
        }
        try {
            List<InventoryDTO> lowStockProducts = inventoryService.getLowStockProducts(threshold);
            return ResponseEntity.ok(lowStockProducts);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    private ResponseEntity<ErrorDTO> handleException(Exception e) {
        ErrorDTO error = new ErrorDTO().code("500");
        return ResponseEntity.status(500).body(error);

    }
}