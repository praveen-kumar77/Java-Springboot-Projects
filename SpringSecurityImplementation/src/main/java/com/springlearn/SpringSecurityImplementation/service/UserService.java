package com.springlearn.SpringSecurityImplementation.service;

import com.springlearn.SpringSecurityImplementation.model.UserCredentials;
import com.springlearn.SpringSecurityImplementation.model.UserProvider;
import com.springlearn.SpringSecurityImplementation.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepo;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public String registerDetails(UserCredentials users) {
        users.setPassword(encoder.encode(users.getPassword()));
        userRepo.save(users);
        return "Saved Successfully";
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserCredentials userName = userRepo.findByUserName(username);

        if (userName == null)
            throw new UsernameNotFoundException("Username Not Found");

        return new UserProvider(userName);
    }
}
