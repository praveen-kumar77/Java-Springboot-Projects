package com.springboot.project.UserManagementService.repo;

import com.springboot.project.UserManagementService.model.UserCred;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserCred, Integer> {

    UserCred findByUsername(String name);
}
