package com.practicum.pu.georgidinov.shoppinglist.service;

import com.practicum.pu.georgidinov.shoppinglist.exception.ValidationCheckException;
import com.practicum.pu.georgidinov.shoppinglist.model.ItemCommand;
import com.practicum.pu.georgidinov.shoppinglist.model.SavedItemCommand;

import java.util.List;

public interface ItemService {

    List<SavedItemCommand> findAllByShoppingUserId(Long userId);

    public SavedItemCommand findById(Long itemCommandId);

    public SavedItemCommand save(Long userId, ItemCommand itemCommand);

    public void deleteById(Long itemCommandId);

    public SavedItemCommand changeItemState(ItemCommand itemCommand, Long itemCommandId) throws ValidationCheckException;

}
