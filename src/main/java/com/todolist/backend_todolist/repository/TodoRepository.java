package com.todolist.backend_todolist.repository;

import com.todolist.backend_todolist.entity.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TodoRepository extends MongoRepository<Todo, Integer>
{

}
