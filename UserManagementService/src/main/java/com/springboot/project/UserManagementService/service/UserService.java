package com.springboot.project.UserManagementService.service;


import com.springboot.project.UserManagementService.model.UserCred;
import com.springboot.project.UserManagementService.model.UserProvider;
import com.springboot.project.UserManagementService.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepo repo;

    @Autowired
    JwtService jwtService;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public UserCred registerDetails(UserCred user){
        user.setPassword(encoder.encode(user.getPassword()));
        user.setCreatedAt(new Date(System.currentTimeMillis()));
        user.setUpdatedAt(new Date(System.currentTimeMillis()));
        return repo.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserCred user = repo.findByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException("Error username not found");
        }

        return new UserProvider(user);
    }

    public ResponseEntity<UserCred> showDetails(int id) {
        UserCred details = repo.findById(id).get();
        return ResponseEntity.ok(details);
    }

    public ResponseEntity<UserCred> updateDetails(UserCred userDetails) {
        userDetails.setUpdatedAt(new Date(System.currentTimeMillis()));
        UserCred saveDetails = repo.save(userDetails);
        return ResponseEntity.ok(saveDetails);
    }

}
