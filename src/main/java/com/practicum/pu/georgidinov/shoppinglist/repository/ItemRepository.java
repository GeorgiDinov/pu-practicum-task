package com.practicum.pu.georgidinov.shoppinglist.repository;

import com.practicum.pu.georgidinov.shoppinglist.entity.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
    List<Item> findAllByOrderById();
}