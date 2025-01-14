package com.springlearn.SpringSecurityJwtTest.service;

import com.springlearn.SpringSecurityJwtTest.model.UserCred;
import com.springlearn.SpringSecurityJwtTest.model.UserProvider;
import com.springlearn.SpringSecurityJwtTest.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepo repo;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public UserCred registerDetails(UserCred user){
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserCred user = repo.findByName(username);

        if(user == null){
            throw new UsernameNotFoundException("Error username not found");
        }

        return new UserProvider(user);
    }
}
