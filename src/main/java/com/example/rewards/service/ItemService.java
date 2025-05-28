package com.example.rewards.service;

import com.example.rewards.model.Item;
import com.example.rewards.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
    /**
     * Retrieves the details of an item by its ID.
     *
     * @param itemId the ID of the item to retrieve
     * @return the Item object containing the details of the item
     * @throws RuntimeException if the item is not found
     */
    public Item getItemDetails(Integer itemId) {
        return this.itemRepository.findById(itemId).orElseThrow(() ->
            new RuntimeException("Item not found with ID: " + itemId)
        );
    }
}
