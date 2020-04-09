package com.example.demo.validator;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.impl.UserService;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    private EmailValidator emailValidator = EmailValidator.getInstance();

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserEntity.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDTO user = (UserDTO) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "Not Empty");
        if(user.getUserName().length() < 6 || user.getUserName().length() > 32) {
            errors.rejectValue("userName", "Size.userForm.username");
        }
        if(userService.findByUserName(user.getUserName()) != null) {
            errors.rejectValue("userName", "Duplicate.userForm.username");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Not Empty");
        if(user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }
        if(!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Not Empty");
        if(!this.emailValidator.isValid(user.getEmail())) {
            errors.rejectValue("email", "Pattern.userForm.email");
        }
        else if(user.getId() == null) {
            UserDTO userDTO = userService.findByEmail(user.getEmail());
            if(userDTO != null) {
                errors.rejectValue("email", "Duplicate.userForm.email");
            }
        }
    }
}
