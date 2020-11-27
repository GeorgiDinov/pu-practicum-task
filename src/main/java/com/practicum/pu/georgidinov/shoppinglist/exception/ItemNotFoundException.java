package com.practicum.pu.georgidinov.shoppinglist.exception;

public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException() {
        super();
    }

    public ItemNotFoundException(String message) {
        super(message);
    }

}
