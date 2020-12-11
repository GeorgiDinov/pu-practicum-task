package com.practicum.pu.georgidinov.shoppinglist.service;

import com.practicum.pu.georgidinov.shoppinglist.command.ItemCommand;
import com.practicum.pu.georgidinov.shoppinglist.command.SavedItemCommand;
import com.practicum.pu.georgidinov.shoppinglist.converter.ItemCommandToItemConverter;
import com.practicum.pu.georgidinov.shoppinglist.converter.ItemToSavedItemCommandConverter;
import com.practicum.pu.georgidinov.shoppinglist.entity.Item;
import com.practicum.pu.georgidinov.shoppinglist.entity.ShoppingListUser;
import com.practicum.pu.georgidinov.shoppinglist.exception.ItemNotFoundException;
import com.practicum.pu.georgidinov.shoppinglist.exception.ValidationCheckException;
import com.practicum.pu.georgidinov.shoppinglist.repository.ItemRepository;
import com.practicum.pu.georgidinov.shoppinglist.repository.ShoppingListUserRepository;
import com.practicum.pu.georgidinov.shoppinglist.validator.BaseNamedEntityValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
public class ItemServiceImpl implements ItemService {

    //== fields ==
    private final ItemRepository itemRepository;
    private final ShoppingListUserRepository userRepository;
    private final BaseNamedEntityValidator baseNamedEntityValidator;

    //== constructors ==
    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository,
                           ShoppingListUserRepository userRepository, BaseNamedEntityValidator baseNamedEntityValidator) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.baseNamedEntityValidator = baseNamedEntityValidator;
    }

    //== public methods ==
    @Override
    public List<SavedItemCommand> findAllByShoppingUserId(Long userId) {
        log.info("Find All Items For userId {}", userId);
        ShoppingListUser user = this.getUserById(userId);
        List<Item> items = this.itemRepository.findAllByUserOrderById(user);
        return this.getSavedItemCommands(items);
    }

    @Override
    public SavedItemCommand findById(Long itemCommandId) {
        log.info("ItemServiceImpl findById()");
        Item item = this.itemRepository
                .findById(itemCommandId)
                .orElseThrow(() -> new ItemNotFoundException("Item with id " + itemCommandId + " NOT FOUND"));
        return ItemToSavedItemCommandConverter.convert(item);
    }

    @Override
    public SavedItemCommand save(Long userId, ItemCommand itemCommand) {
        ShoppingListUser user = this.getUserById(userId);
        Item newItem = ItemCommandToItemConverter.convert(itemCommand, user);
        Item savedItem = this.itemRepository.save(newItem);
        return ItemToSavedItemCommandConverter.convert(savedItem);
    }

    @Override
    public void deleteById(Long itemCommandId) {
        log.info("ItemServiceImpl deleteById()");
        this.itemRepository.deleteById(itemCommandId);
    }

    @Override
    public SavedItemCommand changeItemState(ItemCommand itemCommandToUpdate, Long itemCommandId) throws ValidationCheckException {
        this.baseNamedEntityValidator.validate(itemCommandToUpdate);
        log.info("ItemServiceImpl changeItemState() on {}", itemCommandToUpdate.getName());
        return this.itemRepository.findById(itemCommandId)
                .map(item -> {
                    item.setName(itemCommandToUpdate.getName());
                    item.setQuantity(itemCommandToUpdate.getQuantity());
                    item.setSelected(true);
                    log.info("Updating isSelected Property On Item name={}, quantity={}", item.getName(), item.getQuantity());
                    Item savedItem = this.itemRepository.save(item);
                    return ItemToSavedItemCommandConverter.convert(savedItem);
                }).orElseThrow(() ->
                        new ItemNotFoundException("Item with name = " + itemCommandToUpdate.getName() + " Not Found")
                );
    }

    //== private methods ==
    private ShoppingListUser getUserById(Long id) {
        log.info("In getUserById()");
        return this.userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User Not Found"));
    }

    private List<SavedItemCommand> getSavedItemCommands(List<Item> items) {
        List<SavedItemCommand> savedItemCommands = new ArrayList<>();
        items.forEach(item -> savedItemCommands.add(ItemToSavedItemCommandConverter.convert(item)));
        return savedItemCommands;
    }
}
