package com.practicum.pu.georgidinov.shoppinglist.validator;


import com.practicum.pu.georgidinov.shoppinglist.baseentity.BaseNamedEntity;
import com.practicum.pu.georgidinov.shoppinglist.exception.ValidationCheckException;
import org.springframework.stereotype.Component;

import static com.practicum.pu.georgidinov.shoppinglist.util.ApplicationConstants.VALIDATION_ERROR_ENTITY_ID_IS_NULL;
import static com.practicum.pu.georgidinov.shoppinglist.util.ApplicationConstants.VALIDATION_ERROR_ENTITY_IS_NULL;
import static com.practicum.pu.georgidinov.shoppinglist.util.ApplicationConstants.VALIDATION_ERROR_ENTITY_NAME_IS_NULL;

@Component
public class BaseNamedEntityValidatorImpl implements BaseNamedEntityValidator {

    @Override
    public void validate(BaseNamedEntity entity) throws ValidationCheckException {
        if (entity == null) {
            throw new ValidationCheckException(VALIDATION_ERROR_ENTITY_IS_NULL);
        }
        if (entity.getId() == null) {
            throw new ValidationCheckException(VALIDATION_ERROR_ENTITY_ID_IS_NULL);
        }
        if (entity.getName() == null) {
            throw new ValidationCheckException(VALIDATION_ERROR_ENTITY_NAME_IS_NULL);
        }
    }
}
