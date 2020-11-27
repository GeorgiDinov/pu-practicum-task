package com.practicum.pu.georgidinov.shoppinglist.validator;


import com.practicum.pu.georgidinov.shoppinglist.baseentity.BaseNamedEntity;
import com.practicum.pu.georgidinov.shoppinglist.exception.ValidationCheckException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.practicum.pu.georgidinov.shoppinglist.util.ApplicationConstants.VALIDATION_ERROR_ENTITY_NAME_IS_NULL;

@Component
public class BaseNamedEntityValidatorImpl implements BaseNamedEntityValidator {

    //== fields ==
    private final BaseEntityValidator baseEntityValidator;

    //== constructors ==
    @Autowired
    public BaseNamedEntityValidatorImpl(BaseEntityValidator baseEntityValidator) {
        this.baseEntityValidator = baseEntityValidator;
    }

    //== public methods ==
    @Override
    public void validate(BaseNamedEntity entity) throws ValidationCheckException {
        this.baseEntityValidator.validate(entity);
        if (entity.getName() == null) {
            throw new ValidationCheckException(VALIDATION_ERROR_ENTITY_NAME_IS_NULL);
        }
    }
}
