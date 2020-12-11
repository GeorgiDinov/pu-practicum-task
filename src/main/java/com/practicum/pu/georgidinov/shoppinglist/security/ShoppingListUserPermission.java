package com.practicum.pu.georgidinov.shoppinglist.security;

public enum ShoppingListUserPermission {


    USER_READ("user:read"),
    USER_WRITE("user:write"),
    ITEM_READ("item:read"),
    ITEM_WRITE("item:write");

    private final String permission;

    ShoppingListUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

}
