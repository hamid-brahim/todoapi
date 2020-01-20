package com.hamid.springboot.tut.todos.todos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hamid.springboot.tut.security.AppUser;
import com.hamid.springboot.tut.security.SigninRequest;
import com.hamid.springboot.tut.security.UserService;
import com.jayway.jsonpath.JsonPath;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * @author Hamid Ait Brahim
 * @Created 20/01/2020
 */
@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public abstract class AbstractTodoAppTest {
    private final String USERNAME_FOR_TEST = "brahamid@gmail;com";
    private final String PASSWORD_FOR_TEST = "password";
    private final String NAME_FOR_TEST = "hamid";
    private final String AUTHORIZATION = "Authorization";
    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Before
    public void setup()
    {
        AppUser user = new AppUser(USERNAME_FOR_TEST, new BCryptPasswordEncoder().encode(PASSWORD_FOR_TEST),NAME_FOR_TEST);
        user.setId("111");

        given(userService.loadUserByUsername(user.getUsername())).willReturn(user);
    }

    public ResultActions login(String username, String password) throws Exception
    {
        SigninRequest signinRequest = new SigninRequest(username, password);
        return mockMvc.perform(post("/api/v1/auth")
             .contentType(MediaType.APPLICATION_JSON)
             .content(new ObjectMapper().writeValueAsString(signinRequest)));
    }
    public MockHttpServletRequestBuilder doGet(String url)
    {
        return get(url).header(AUTHORIZATION, getHeader());
    }
    private String getHeader()
    {
        try{
           MvcResult result = login(USERNAME_FOR_TEST, PASSWORD_FOR_TEST).andReturn();
           String token = JsonPath.read(result.getResponse().getContentAsString(), "$.token");
           String header = String.format("Bearer %s", token);

           return header;
        }
        catch (Exception ex)
        {
            return null;
        }

    }
}
