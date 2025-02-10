package com.springlearn.StudentManagementService.repo;

import com.springlearn.StudentManagementService.model.UserCred;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserCred, String> {
    UserCred findByUserName(String username);


}

