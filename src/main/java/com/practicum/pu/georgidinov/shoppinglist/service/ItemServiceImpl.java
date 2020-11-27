package com.practicum.pu.georgidinov.shoppinglist.service;

import com.practicum.pu.georgidinov.shoppinglist.entity.Item;
import com.practicum.pu.georgidinov.shoppinglist.repository.BaseEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    //== fields ==
    private final BaseEntityRepository repository;

    //== constructors ==
    @Autowired
    public ItemServiceImpl(BaseEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Item> findAllItems() {
        return (List<Item>) this.repository.findAll();
    }
}
