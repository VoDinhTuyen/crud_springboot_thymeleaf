package com.example.demo.validator;

import com.example.demo.dto.PostDTO;
import com.example.demo.entity.PostEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PostValidator  implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return PostEntity.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PostDTO postDTO = (PostDTO) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "field.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "categoryCode", "field.empty");
    }
}
