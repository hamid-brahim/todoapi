package com.hamid.springboot.tut.todos;

import com.hamid.springboot.tut.error.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.given;

/**
 * @author Hamid Ait Brahim
 * @Created 14/01/2020
 */
@RunWith(SpringRunner.class)
public class TodoServiceTest {

    @MockBean
    private TodoRepository todoRepository;

    @Autowired
    private TodoService todoService;

    @TestConfiguration
    static class TodoServiceContextConfiguration
    {
        @Bean
        public TodoService todoService()
        {
            return new TodoService();
        }
    }
    @Test
    public void whenFindAll_returnTodolist()
    {
        Todo todo1 = new Todo("1111", "Test1", "Todo test");
        Todo todo2 = new Todo("5525", "todo2","Test todo 2");
        Todo todo3 = new Todo("8888", "Todo", "This is a todo");

        Object[] array;
        List todos = Arrays.asList(todo1, todo2, todo3);

        given(todoRepository.findAll()).willReturn(todos);

        assertThat(todoService.findAll()).hasSize(3).contains(todo1,todo2,todo3);
    }
    @Test
    public void whenGetById_TodoShowIdFound()
    {
        Todo todo = new Todo("8888", "Todo", "This is a todo");
        given(todoRepository.findById(anyString())).willReturn(Optional.ofNullable(todo));

        Todo result = todoService.getById("988");
        assertThat(result.getTitle()).containsIgnoringCase("todo");
    }
    @Test(expected = NotFoundException.class)
    public void whenInvalidId_TodoShouldNotBefound(){
        given(todoRepository.findById(anyString())).willReturn(Optional.empty());

        todoService.getById("8888");
    }
}

