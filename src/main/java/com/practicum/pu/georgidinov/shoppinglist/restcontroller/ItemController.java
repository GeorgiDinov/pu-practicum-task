package com.practicum.pu.georgidinov.shoppinglist.restcontroller;


import com.practicum.pu.georgidinov.shoppinglist.entity.Item;
import com.practicum.pu.georgidinov.shoppinglist.exception.ValidationCheckException;
import com.practicum.pu.georgidinov.shoppinglist.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/items")
public class ItemController {

    //== fields ==
    private final ItemService itemService;

    //== constructors ==
    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }


    //== public methods ==
    @GetMapping()
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<Item> getAllItems() {
        log.info("ItemController getAllItems()");
        return this.itemService.findAllItems();
    }

    @GetMapping("/{itemId}")
    @PreAuthorize("hasAuthority('item:read')")
    public Item getItemById(@PathVariable String itemId) {
        log.info("ItemController getItemById()");
        log.info("id value passed = {}", itemId);
        return this.itemService.findById(Long.valueOf(itemId));
    }

    @DeleteMapping("/{itemId}")
    @PreAuthorize("hasAuthority('item:write')")
    public void deleteItemById(@PathVariable String itemId) {
        log.info("ItemController deleteItemById()");
        log.info("id value passed = {}", itemId);
        this.itemService.deleteById(Long.valueOf(itemId));
    }

    @PutMapping("/{itemId}")
    @PreAuthorize("hasAuthority('item:write')")
    public Item changeItemState(@RequestBody Item itemToUpdate, @PathVariable String itemId) throws ValidationCheckException {
        log.info("ItemController changeItemState(Item itemToUpdate, String itemId)");
        log.info("id value passed = {}", itemId);
        return this.itemService.changeItemState(itemToUpdate, Long.valueOf(itemId));
    }

    @PostMapping("/newItem")
    @PreAuthorize("hasAuthority('item:write')")
    public Item addNewItem(@RequestBody Item item) {
        log.info("ItemController addNewItem()");
        log.info("item passed = {}", item);
        return this.itemService.save(item);
    }
}