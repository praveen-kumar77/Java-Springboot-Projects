package com.springlearn.StudentManagementService.service;

import com.springlearn.StudentManagementService.model.UserCred;
import com.springlearn.StudentManagementService.model.UserProvider;
import com.springlearn.StudentManagementService.repo.UserRepo;
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

        UserCred user = repo.findByUserName(username);

        if(user == null){
            throw new UsernameNotFoundException("Error username not found");
        }

        return new UserProvider(user);
    }

    public UserCred getUserDetails(int id) {
        return repo.findById(id).get();
    }

    public UserCred updateUserDetails(UserCred user) {
        return repo.save(user);
    }
}
