package com.todolist.backend_todolist.service;

import com.todolist.backend_todolist.entity.Todo;

import java.util.List;

public interface TodoService
{
    public List<Todo> getTodos();

    public Todo addTodo(Todo product);

    public List<Todo> deleteTodos();

    public List<Todo> defaultsTodos();

    public Todo updateEtat(int id, int etat);
}
