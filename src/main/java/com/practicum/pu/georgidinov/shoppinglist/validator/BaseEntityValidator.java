package com.practicum.pu.georgidinov.shoppinglist.validator;


import com.practicum.pu.georgidinov.shoppinglist.baseentity.BaseEntity;
import com.practicum.pu.georgidinov.shoppinglist.exception.ValidationCheckException;

public interface BaseEntityValidator {

    void validate(BaseEntity entity) throws ValidationCheckException;

}
