package com.practicum.pu.georgidinov.shoppinglist.repository;

import com.practicum.pu.georgidinov.shoppinglist.entity.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseEntityRepository extends CrudRepository<Item, Long> {
}
