package com.userauthentication.demo;

import com.userauthentication.demo.dao.RoleRepository;
import com.userauthentication.demo.dao.UserRepository;
import com.userauthentication.demo.entities.Role;
import com.userauthentication.demo.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.Collections;
import java.util.Set;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private Validator validator;

    @GetMapping("/login")
    public String login() {

        return "login";
    }

    @GetMapping(value = {"/", "/home"})
    public String home() {

        return "home";
    }

    @GetMapping("/addUser")
    public String registrationForm(Model model) {

        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/addUser")
    public String handleRegistration(@Valid User user, BindingResult bindingResult) {

        Set<ConstraintViolation<@Valid User>> validate = validator.validate(user);

        if (!validate.isEmpty() ||bindingResult.hasErrors()) {

            return "addUser";

        }
        String password = user.getPassword();
        String matchingPassword = user.getMatchingPassword();

        org.springframework.security.crypto.password.PasswordEncoder encoder
                = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();

        String encodePassword = encoder.encode(password);
        System.out.println(password);
        System.out.println(encodePassword);
        System.out.println("Is encoder & password matches " + encoder.matches(password, encodePassword));

        user.setPassword(encodePassword);
        user.setMatchingPassword(matchingPassword);
        user.setActive(1);
        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(Collections.singletonList(userRole));
        userRepository.save(user);

        return "redirect:/home";

    }
}
