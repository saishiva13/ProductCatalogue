package com.project.Product_Catalogue.service;

import org.openapitools.model.InventoryDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements InventoryService{
    private final List<InventoryDTO> inventoryList = new ArrayList<>();

    @Override
    public InventoryDTO updateInventory(InventoryDTO inventoryDTO) {
        InventoryDTO existingInventory = inventoryList.stream()
                .filter(inventory -> inventory.getProductId().equals(inventoryDTO.getProductId()))
                .findFirst()
                .orElse(null);

        if (existingInventory != null) {
            existingInventory.setQuantity(inventoryDTO.getQuantity());
            existingInventory.setLastUpdated(inventoryDTO.getLastUpdated());
        } else {
            inventoryList.add(inventoryDTO);
        }

        return inventoryDTO;
    }

    @Override
    public List<InventoryDTO> getAllInventory() {
        return new ArrayList<>(inventoryList);
    }

    @Override
    public List<InventoryDTO> getLowStockProducts(Integer threshold) {
        return inventoryList.stream()
                .filter(inventory -> inventory.getQuantity() < threshold)
                .collect(Collectors.toList());
    }
}
