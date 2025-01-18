package com.springlearn.SpringSecurityImplementation.controller;

import com.springlearn.SpringSecurityImplementation.config.SecurityConfig;
import com.springlearn.SpringSecurityImplementation.model.UserCredentials;
import com.springlearn.SpringSecurityImplementation.service.UserCredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/security")
public class MainController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserCredentialService userService;

    @GetMapping("/")
    public String home(){
        return "home called";
    }

    @PostMapping("register")
    public String registerCredentials(@RequestBody UserCredentials users){
        System.out.println("reached");
        return userService.registerDetails(users);
    }

    @PostMapping("login")
    public String Login(@RequestBody UserCredentials user){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));

        if(authentication.isAuthenticated())
            return "Login Successfully";
        else
            return "Login Failed Successfully :) ";
    }

}
