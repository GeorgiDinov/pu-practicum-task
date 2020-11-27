package com.practicum.pu.georgidinov.shoppinglist.service;

import com.practicum.pu.georgidinov.shoppinglist.entity.Item;
import com.practicum.pu.georgidinov.shoppinglist.exception.ItemNotFoundException;
import com.practicum.pu.georgidinov.shoppinglist.exception.ValidationCheckException;
import com.practicum.pu.georgidinov.shoppinglist.repository.ItemRepository;
import com.practicum.pu.georgidinov.shoppinglist.validator.BaseNamedEntityValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ItemServiceImpl implements ItemService {

    //== fields ==
    private final ItemRepository repository;
    private final BaseNamedEntityValidator baseNamedEntityValidator;

    //== constructors ==
    @Autowired
    public ItemServiceImpl(ItemRepository repository,
                           BaseNamedEntityValidator baseNamedEntityValidator) {
        this.repository = repository;
        this.baseNamedEntityValidator = baseNamedEntityValidator;
    }

    //== public methods ==
    @Override
    public List<Item> findAllItems() {
        return this.repository.findAllByOrderById();
    }

    @Override
    public Item findById(Long id) {
        log.info("ItemServiceImpl findById()");
        Optional<Item> optionalItem = this.repository.findById(id);
        return optionalItem.orElseThrow(() -> new ItemNotFoundException("Item Not Found"));
    }

    @Override
    public Item save(Item item) {
        log.info("ItemServiceImpl save()");
        return this.repository.save(item);
    }

    @Override
    public void deleteById(Long id) {
        log.info("ItemServiceImpl deleteById()");
        this.repository.deleteById(id);
    }

    @Override
    public Item changeItemState(Item itemToUpdate, Long id) throws ValidationCheckException {
        log.info("ItemServiceImpl changeItemState(Item itemToUpdate, Long id)");
        this.baseNamedEntityValidator.validate(itemToUpdate);
        return this.repository.findById(id).map(item -> {
            item.setName(itemToUpdate.getName());
            item.setQuantity(itemToUpdate.getQuantity());
            item.setSelected(true);
            log.info("Updating isSelected Property On Item name={}, quantity={}", item.getName(), item.getQuantity());
            return repository.save(item);
        }).orElseGet(() -> {
            itemToUpdate.setId(id);
            log.info("Saving as new item {}, {}", itemToUpdate.getName(), itemToUpdate.getQuantity());
            return repository.save(itemToUpdate);
        });
    }
}
