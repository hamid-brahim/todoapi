package com.hamid.springboot.tut.todos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Hamid Ait Brahim
 * @Created 07/01/2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Todo {
    @Id
    private String id;
    @NotNull(message = "Title is required")
    @Size(min = 3, message = "Title must be at least 3 characters long")
    private String title;

    @NotNull(message = "Description is required!")
    private String description;
    private Long timestamp = System.currentTimeMillis();
    private String userId;

    public Todo(String id, String title, String description)
    {
        this.id= id;
        this.title = title;
        this.description = description;

    }
}
