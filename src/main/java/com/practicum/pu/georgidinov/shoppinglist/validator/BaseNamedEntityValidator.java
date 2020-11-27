package com.practicum.pu.georgidinov.shoppinglist.validator;


import com.practicum.pu.georgidinov.shoppinglist.baseentity.BaseNamedEntity;
import com.practicum.pu.georgidinov.shoppinglist.exception.ValidationCheckException;

public interface BaseNamedEntityValidator {
    void validate(BaseNamedEntity entity) throws ValidationCheckException;
}
