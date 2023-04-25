package com.todolist.backend_todolist.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "todos")
public class Todo
{
    public static final String SEQUENCE_NAME="todo_sequence";

    @Id
    private Integer id;
    private String titre;
    private String description;
    private Integer etat;
    private String date;
}
