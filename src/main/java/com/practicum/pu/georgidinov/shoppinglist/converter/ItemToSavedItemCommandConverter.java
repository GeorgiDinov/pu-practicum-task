package com.practicum.pu.georgidinov.shoppinglist.converter;

import com.practicum.pu.georgidinov.shoppinglist.entity.Item;
import com.practicum.pu.georgidinov.shoppinglist.model.SavedItemCommand;

public class ItemToSavedItemCommandConverter {

    public static SavedItemCommand convert(Item item) {
        return SavedItemCommand.builder()
                .id(item.getId())
                .name(item.getName())
                .quantity(item.getQuantity())
                .isSelected(item.isSelected())
                .build();
    }

}