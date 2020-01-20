package com.hamid.springboot.tut.security;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Hamid Ait Brahim
 * @Created 20/01/2020
 */
public interface UserRepository extends MongoRepository<AppUser, String> {

    AppUser findByEmail(String email);
}

