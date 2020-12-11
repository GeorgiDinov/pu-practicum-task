package com.practicum.pu.georgidinov.shoppinglist.restcontroller;


import com.practicum.pu.georgidinov.shoppinglist.command.ItemCommand;
import com.practicum.pu.georgidinov.shoppinglist.command.SavedItemCommand;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class ItemController {

    //== fields ==
    private final ItemService itemService;

    //== constructors ==
    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }


    //== public methods ==
    @GetMapping("/{userId}/items")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<SavedItemCommand> getAllItems(@PathVariable String userId) {
        log.info("ItemController getAllItems() userId = {}", userId);
        return this.itemService.findAllByShoppingUserId(Long.valueOf(userId));
    }

    @GetMapping("/items/{itemCommandId}")
    @PreAuthorize("hasAuthority('item:read')")
    public SavedItemCommand getSavedItemCommandById(@PathVariable String itemCommandId) {
        log.info("ItemController getItemById()");
        log.info("itemCommandId value passed = {}", itemCommandId);
        return this.itemService.findById(Long.valueOf(itemCommandId));
    }

    @DeleteMapping("/items/{itemCommandId}")
    @PreAuthorize("hasAuthority('item:write')")
    public void deleteItemById(@PathVariable String itemCommandId) {
        log.info("ItemController deleteItemById()");
        log.info("itemId value passed = {}", itemCommandId);
        this.itemService.deleteById(Long.valueOf(itemCommandId));
    }

    @PutMapping("/items/{itemCommandId}")
    @PreAuthorize("hasAuthority('item:write')")
    public SavedItemCommand changeItemState(@RequestBody ItemCommand itemCommandToUpdate,
                                @PathVariable String itemCommandId) throws ValidationCheckException {
        log.info("ItemController changeItemState(Item itemToUpdate, String itemId)");
        log.info("itemId value passed = {}", itemCommandId);
        return this.itemService.changeItemState(itemCommandToUpdate, Long.valueOf(itemCommandId));
    }

    @PostMapping("/{userId}/items/newItem")
    @PreAuthorize("hasAuthority('item:write')")
    public SavedItemCommand addNewItem(@RequestBody ItemCommand itemCommand,
                           @PathVariable String userId) {
        log.info("ItemController addNewItem()");
        log.info("userId passed = {}, item passed = {}", userId, itemCommand);
        return this.itemService.save(Long.valueOf(userId), itemCommand);
    }
}