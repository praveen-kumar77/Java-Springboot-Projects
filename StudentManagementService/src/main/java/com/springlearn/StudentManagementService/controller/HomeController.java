package com.springlearn.StudentManagementService.controller;

import com.springlearn.StudentManagementService.model.UserCred;
import com.springlearn.StudentManagementService.repo.UserRepo;
import com.springlearn.StudentManagementService.service.JwtService;
import com.springlearn.StudentManagementService.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/user")
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    UserRepo userRepo;

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserCred userCred) {
        return userService.registerDetails(userCred);
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserCred userCred, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userCred.getUserName(), userCred.getPassword()));

        if (authentication.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtService.generateToken(userCred.getUserName());

            Cookie cookie = new Cookie("token", token);
            cookie.setHttpOnly(true);
            cookie.setSecure(true); // Use HTTPS in production
            cookie.setPath("/");
            response.addCookie(cookie);

            UserCred role = userRepo.findByUserName(userCred.getUserName());
            Cookie roleCookie = new Cookie("userRole", role.getUserRole().toString());
            roleCookie.setPath("/");
            response.addCookie(roleCookie);

            return "redirect:/api/user/home"; // Redirect after successful login
        } else {
            return "redirect:/api/user/login?error"; // Redirect to login page with error
        }
    }


    @GetMapping("/logout")
    public String logout(HttpServletResponse response) {

        // Delete cookie
        Cookie logoutCookie = new Cookie("token", null);
        logoutCookie.setHttpOnly(true);
        logoutCookie.setSecure(true); // Use only if using HTTPS
        logoutCookie.setPath("/");
        logoutCookie.setMaxAge(0); // Delete cookie immediately
        response.addCookie(logoutCookie);

        Cookie roleCookie = new Cookie("userRole", null);
        roleCookie.setPath("/");
        roleCookie.setMaxAge(0); // Delete cookie immediately
        response.addCookie(roleCookie);

        return "redirect:/api/user/login";
    }

}
