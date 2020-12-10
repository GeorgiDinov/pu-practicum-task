package com.practicum.pu.georgidinov.shoppinglist.service;

import com.practicum.pu.georgidinov.shoppinglist.entity.Item;
import com.practicum.pu.georgidinov.shoppinglist.exception.ValidationCheckException;

import java.util.List;

public interface ItemService {

    List<Item> findAllItems();

    List<Item> findAllByShoppingUserId(Long userId);

    public Item findById(Long id);

    public Item save(Long userId, Item item);

    public void deleteById(Long id);

    public Item changeItemState(Item item, Long id) throws ValidationCheckException;

}
