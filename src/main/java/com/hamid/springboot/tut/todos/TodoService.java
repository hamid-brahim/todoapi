package com.hamid.springboot.tut.todos;

import com.hamid.springboot.tut.error.ConflictException;
import com.hamid.springboot.tut.error.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Hamid Ait Brahim
 * @Created 07/01/2020
 */
@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> findAll()
    {

        return todoRepository.findAll();
    }

    public List<Todo> findByUser(String id)
    {
        return todoRepository.findByUserId(id);
    }

    public Todo getById(String id)
    {
        try{
            return todoRepository.findById(id).get();
        }catch (NoSuchElementException ex)
        {
          throw new NotFoundException(String.format("No record with the id [%s] was found in our database", id));
        }

    }

    public  Todo save(Todo todo)
    {
        if(todoRepository.findByTitle(todo.getTitle())!= null)
        {
            throw new ConflictException(String.format("Another todo with the title [%s] exists already!", todo.getTitle()));
        }
        return todoRepository.save(todo);
    }

    public void deleteTodo(String id)
    {
      todoRepository.deleteById(id);
    }

}
