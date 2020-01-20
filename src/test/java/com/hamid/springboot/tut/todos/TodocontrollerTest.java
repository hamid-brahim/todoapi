package com.hamid.springboot.tut.todos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hamid.springboot.tut.todos.todos.AbstractTodoAppTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Hamid Ait Brahim
 * @Created 14/01/2020
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TodocontrollerTest extends AbstractTodoAppTest {


    @MockBean
    private TodoService todoService;

    @Test
    public void whenGetAllTodos_thenReturnJsonArray() throws Exception{
        Todo todo1 = new Todo("1111", "Test1", "Todo test");
        Todo todo2 = new Todo("5525", "todo2","Test todo 2");
        Todo todo3 = new Todo("8888", "Todo", "This is a todo");
        List<Todo> data = Arrays.asList(todo1, todo2, todo3);

        given(todoService.findAll()).willReturn(data);

        mockMvc.perform(get("/api/v1/todos").contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].title",equalTo(todo1.getTitle())));
    }
    @Test
    public void whenPostTodo_thenCreateTodo() throws Exception{
        Todo todo1 = new Todo("1111", "Test1", "Todo test");
        ObjectMapper mapper = new ObjectMapper();

        given(todoService.save(todo1)).willReturn(todo1);

        mockMvc.perform(post("/api/v1/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(todo1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title", is(todo1.getTitle())));
    }
}
