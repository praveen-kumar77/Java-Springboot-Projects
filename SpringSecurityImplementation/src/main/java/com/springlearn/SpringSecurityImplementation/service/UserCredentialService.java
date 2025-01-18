package com.springlearn.SpringSecurityImplementation.service;

import com.springlearn.SpringSecurityImplementation.model.UserCredentials;
import com.springlearn.SpringSecurityImplementation.model.UserDetailsProvider;
import com.springlearn.SpringSecurityImplementation.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserCredentialService implements UserDetailsService {

    @Autowired
    UserRepository userRepo;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    public String registerDetails(UserCredentials users) {
        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
        userRepo.save(users);
        return "saved";
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserCredentials user = userRepo.findByName(username);

        if(user == null){
            throw new UsernameNotFoundException("Error! UserName Not Found");
        }

        return new UserDetailsProvider(user);
    }
}
