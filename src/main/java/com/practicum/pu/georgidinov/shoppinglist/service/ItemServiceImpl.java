package com.practicum.pu.georgidinov.shoppinglist.service;

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

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
    public List<Item> findAllByShoppingUserId(Long userId) {
        log.info("Find All Items For userId {}", userId);
        ShoppingListUser user = this.getUserById(userId);
        List<Item> items = this.itemRepository.findAllByUserOrderById(user);
        items.forEach(System.out::println);
        return items;
    }

    @Override
    public List<Item> findAllItems() {
        return this.itemRepository.findAllByOrderById();
    }

    @Override
    public Item findById(Long id) {
        log.info("ItemServiceImpl findById()");
        Optional<Item> optionalItem = this.itemRepository.findById(id);
        return optionalItem.orElseThrow(() -> new ItemNotFoundException("Item Not Found"));
    }

    @Override
    public Item save(Long userId, Item item) {
        log.info("ItemServiceImpl save()");
        ShoppingListUser user = this.getUserById(userId);
        log.info("after getUserById()");
        Item newItem = Item.builder()
                .name(item.getName())
                .quantity(item.getQuantity())
                .isSelected(false)
                .user(user)
                .build();
        user.getItems().add(newItem);
        log.info("before itemRepository save()");
        return this.itemRepository.save(newItem);
    }

    @Override
    public void deleteById(Long id) {
        log.info("ItemServiceImpl deleteById()");
        this.itemRepository.deleteById(id);
    }

    @Override
    public Item changeItemState(Item itemToUpdate, Long id) throws ValidationCheckException {
        log.info("ItemServiceImpl changeItemState(Item itemToUpdate, Long id)");
        this.baseNamedEntityValidator.validate(itemToUpdate);
        return this.itemRepository.findById(id).map(item -> {
            item.setName(itemToUpdate.getName());
            item.setQuantity(itemToUpdate.getQuantity());
            item.setSelected(true);
            log.info("Updating isSelected Property On Item name={}, quantity={}", item.getName(), item.getQuantity());
            return itemRepository.save(item);
        }).orElseGet(() -> {
            itemToUpdate.setId(id);
            log.info("Saving as new item {}, {}", itemToUpdate.getName(), itemToUpdate.getQuantity());
            return itemRepository.save(itemToUpdate);
        });
    }

    //== private methods ==
    private ShoppingListUser getUserById(Long id) {
        log.info("In getUserById()");
        return this.userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User Not Found"));
    }
}
