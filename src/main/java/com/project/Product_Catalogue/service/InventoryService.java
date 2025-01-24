package com.project.Product_Catalogue.service;

import org.openapitools.model.InventoryDTO;

import java.util.List;

public interface InventoryService {
    InventoryDTO updateInventory(InventoryDTO inventoryDTO);
    List<InventoryDTO> getAllInventory();
    List<InventoryDTO> getLowStockProducts(Integer threshold);
}
