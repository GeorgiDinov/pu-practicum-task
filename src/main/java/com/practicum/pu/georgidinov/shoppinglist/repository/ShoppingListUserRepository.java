package com.practicum.pu.georgidinov.shoppinglist.repository;

import com.practicum.pu.georgidinov.shoppinglist.entity.ShoppingListUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingListUserRepository extends CrudRepository<ShoppingListUser, Long> {

}
