package com.springlearn.SpringSecurityJwtTest.controller;

import com.springlearn.SpringSecurityJwtTest.model.UserCred;
import com.springlearn.SpringSecurityJwtTest.service.JwtService;
import com.springlearn.SpringSecurityJwtTest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    UserService service;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;



    @GetMapping("/home")
    public String home(){
        return "welcome to great kirigalan magic show";
    }

    @PostMapping("register")
    public UserCred register(@RequestBody UserCred user){
        return service.registerDetails(user);
    }

    @PostMapping("login")
    public String login(@RequestBody UserCred user){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));

        if(authentication.isAuthenticated())
            return jwtService.generateToken(user.getName());
        else
            return "Login Failed";

    }

}
