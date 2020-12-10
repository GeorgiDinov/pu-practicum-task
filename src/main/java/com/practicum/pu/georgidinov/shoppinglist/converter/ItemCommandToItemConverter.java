package com.practicum.pu.georgidinov.shoppinglist.converter;

import com.practicum.pu.georgidinov.shoppinglist.command.ItemCommand;
import com.practicum.pu.georgidinov.shoppinglist.entity.Item;
import com.practicum.pu.georgidinov.shoppinglist.entity.ShoppingListUser;

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
