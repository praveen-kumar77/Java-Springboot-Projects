package com.springlearn.StudentManagementService.controller;


import com.springlearn.StudentManagementService.model.UserCred;
import com.springlearn.StudentManagementService.service.JwtService;
import com.springlearn.StudentManagementService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class HomeController {

    @Autowired
    UserService service;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @GetMapping("/home")
    public String home(){
        return "Welcome Home";
    }

    @PostMapping("/register")
    public UserCred register(@RequestBody UserCred user){
        return service.registerDetails(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserCred user){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));

        if(authentication.isAuthenticated())
            return jwtService.generateToken(user.getUserName());
        else
            return "Login Failed";
    }

    @GetMapping("{id}")
    public UserCred getUser(@PathVariable int id){
        return service.getUserDetails(id);
    }

    @PutMapping("/update")
    public UserCred update(@RequestBody UserCred user){
        return service.updateUserDetails(user);
    }

}
