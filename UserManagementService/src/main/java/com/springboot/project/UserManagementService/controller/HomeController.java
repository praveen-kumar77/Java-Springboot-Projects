package com.springboot.project.UserManagementService.controller;


import com.springboot.project.UserManagementService.model.UserCred;
import com.springboot.project.UserManagementService.service.JwtService;
import com.springboot.project.UserManagementService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/users")
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
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if(authentication.isAuthenticated()) {

            return jwtService.generateToken(user.getUsername());
        }
        else
            return "Login Failed";

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserCred> userDetails(@PathVariable int id){
        return service.showDetails(id);
    }

    @PutMapping("/update")
    public ResponseEntity<UserCred> updateDetails(@RequestBody UserCred userDetails){
        return service.updateDetails(userDetails);
    }

}
