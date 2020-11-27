package com.practicum.pu.georgidinov.shoppinglist.validator;


import com.practicum.pu.georgidinov.shoppinglist.baseentity.BaseEntity;
import com.practicum.pu.georgidinov.shoppinglist.exception.ValidationCheckException;
import org.springframework.stereotype.Component;

import static com.practicum.pu.georgidinov.shoppinglist.util.ApplicationConstants.VALIDATION_ERROR_ENTITY_ID_IS_NULL;
import static com.practicum.pu.georgidinov.shoppinglist.util.ApplicationConstants.VALIDATION_ERROR_ENTITY_IS_NULL;

@Component
public class BaseEntityValidatorImpl implements BaseEntityValidator {

    @Override
    public void validate(BaseEntity entity) throws ValidationCheckException {
        if (entity == null) {
            throw new ValidationCheckException(VALIDATION_ERROR_ENTITY_IS_NULL);
        }
        if (entity.getId() == null) {
            throw new ValidationCheckException(VALIDATION_ERROR_ENTITY_ID_IS_NULL);
        }
    }

}
