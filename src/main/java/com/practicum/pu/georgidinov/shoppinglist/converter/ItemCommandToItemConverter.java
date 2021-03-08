package com.practicum.pu.georgidinov.shoppinglist.converter;

import com.practicum.pu.georgidinov.shoppinglist.entity.Item;
import com.practicum.pu.georgidinov.shoppinglist.entity.ShoppingListUser;
import com.practicum.pu.georgidinov.shoppinglist.model.ItemCommand;

public class ItemCommandToItemConverter {

    public static Item convert(ItemCommand itemCommand, ShoppingListUser user) {
        return Item.builder()
                .name(itemCommand.getName())
                .quantity(itemCommand.getQuantity())
                .isSelected(false)
                .user(user)
                .build();
    }
}
