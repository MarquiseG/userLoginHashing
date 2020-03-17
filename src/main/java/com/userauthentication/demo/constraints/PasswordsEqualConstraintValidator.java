package com.userauthentication.demo.constraints;


import com.userauthentication.demo.entities.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PasswordsEqualConstraintValidator implements
        ConstraintValidator<PasswordsEqualConstraint, Object> {

    private  Pattern BCRYPT_PATTERN = Pattern.compile("\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}");
    @Autowired
    org.springframework.security.crypto.password.PasswordEncoder encoder;
    @Override
    public void initialize(PasswordsEqualConstraint arg0) {
    }

    @Override
    public boolean isValid(Object candidate, ConstraintValidatorContext arg1) {
        User user = (User) candidate;

        //Check if the password is encoded or not
        if (BCRYPT_PATTERN.matcher(user.getPassword()).matches()) {

            encoder = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();

             return encoder.matches(user.getMatchingPassword(), user.getPassword());

        }else {

            return user.getPassword().equals(user.getMatchingPassword());

        }
    }
}
