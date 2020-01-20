package com.hamid.springboot.tut.todos;

import com.hamid.springboot.tut.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Hamid Ait Brahim
 * @Created 07/01/2020
 */
@RestController
@RequestMapping(value = "/api/v1/todos")
public class TodoController extends BaseController {

    @Autowired
    private TodoService todoService;

    @GetMapping(value = {"", "/"})
    public ResponseEntity<List<Todo>> listTodos()
    {

        List<Todo> result = todoService.findByUser(getCurrentUser().getId());
        return new ResponseEntity<>(result,HttpStatus.OK);

    }
    @GetMapping(value="/{id}")
    public ResponseEntity<Todo> findById(@PathVariable String id)
    {
        Todo result= todoService.getById(id);
        return new ResponseEntity<Todo>(result, HttpStatus.OK);
    }

    @PostMapping(value = {"","/"})
    public ResponseEntity<Todo> createNewTodo(@Valid @RequestBody Todo todo)
    {
        todo.setUserId(getCurrentUser().getId());
        Todo result = todoService.save(todo);
       return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String id)
    {
        todoService.deleteTodo(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
