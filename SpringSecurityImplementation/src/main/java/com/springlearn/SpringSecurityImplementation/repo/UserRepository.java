package com.springlearn.SpringSecurityImplementation.repo;

import com.springlearn.SpringSecurityImplementation.model.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserCredentials, String> {

    UserCredentials findByName(String name);

}
