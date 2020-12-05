package com.practicum.pu.georgidinov.shoppinglist.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.practicum.pu.georgidinov.shoppinglist.security.ShoppingListUserPermission.ITEM_READ;
import static com.practicum.pu.georgidinov.shoppinglist.security.ShoppingListUserPermission.ITEM_WRITE;
import static com.practicum.pu.georgidinov.shoppinglist.security.ShoppingListUserPermission.USER_READ;
import static com.practicum.pu.georgidinov.shoppinglist.security.ShoppingListUserPermission.USER_WRITE;

public enum ShoppingListUserRole {

    USER(Sets.newHashSet(ITEM_READ, ITEM_WRITE)),
    ADMIN(Sets.newHashSet(USER_READ, USER_WRITE));

    //== fields ==
    private final Set<ShoppingListUserPermission> permissions;

    //== constructors ==
    ShoppingListUserRole(Set<ShoppingListUserPermission> permissions) {
        this.permissions = permissions;
    }

    //== public methods ==
    public Set<ShoppingListUserPermission> getPermissions() {
        return this.permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }

}