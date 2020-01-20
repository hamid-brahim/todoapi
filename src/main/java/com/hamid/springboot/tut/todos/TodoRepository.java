package com.hamid.springboot.tut.todos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Hamid Ait Brahim
 * @Created 07/01/2020
 */
@Repository
public interface TodoRepository extends MongoRepository<Todo,String> {

    Todo findByTitle(String title);
    List<Todo> findByUserId(String userid);
}
