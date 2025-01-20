package com.springlearn.SpringSecurityImplementation.controller;

import com.springlearn.SpringSecurityImplementation.model.UserCredentials;
import com.springlearn.SpringSecurityImplementation.service.JwtService;
import com.springlearn.SpringSecurityImplementation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/security")
public class MainController {

    @Autowired
    UserService userService;

    @Autowired
    JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    @GetMapping("/")
    public String home(){
        return "home page is here";
    }

    @PostMapping("register")
    public String register(@RequestBody UserCredentials users){
        return userService.registerDetails(users);
    }

    @PostMapping("login")
    public String login(@RequestBody UserCredentials users){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(users.getUserName(), users.getPassword()));

        if (authentication.isAuthenticated())
            return jwtService.generateToken(users);
        else
            return "Failed";
    }

}
