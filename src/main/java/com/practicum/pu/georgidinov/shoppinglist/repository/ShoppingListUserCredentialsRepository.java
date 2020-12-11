package com.practicum.pu.georgidinov.shoppinglist.repository;

import com.practicum.pu.georgidinov.shoppinglist.entity.ShoppingListUserCredentials;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingListUserCredentialsRepository extends CrudRepository<ShoppingListUserCredentials, Long> {
    Optional<ShoppingListUserCredentials> findByUsername(String username);
}
