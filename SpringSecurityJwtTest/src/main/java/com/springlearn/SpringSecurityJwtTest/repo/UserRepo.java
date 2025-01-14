package com.springlearn.SpringSecurityJwtTest.repo;

import com.springlearn.SpringSecurityJwtTest.model.UserCred;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<UserCred, String > {

    UserCred findByName(String name);
}
