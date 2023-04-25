package com.todolist.backend_todolist.service.implementation;

import com.todolist.backend_todolist.entity.Todo;
import com.todolist.backend_todolist.repository.TodoRepository;
import com.todolist.backend_todolist.service.SequenceGeneratorService;
import com.todolist.backend_todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService
{
    @Autowired
    private TodoRepository todoRepo;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Override
    public List<Todo> getTodos()
    {
        return todoRepo.findAll();
    }

    @Override
    public Todo addTodo(Todo todo)
    {
        return todoRepo.save(todo);
    }

    @Override
    public List<Todo> deleteTodos()
    {
        List<Todo> todos = todoRepo.findAll();
        todoRepo.deleteAll(todos);
        return todos;
    }

    private Todo createTodo(String titre, String description, int etat, String date)
    {
        Todo todo = new Todo();
        todo.setId(sequenceGeneratorService.getSequenceNumber(Todo.SEQUENCE_NAME));
        todo.setTitre(titre);
        todo.setDescription(description);
        todo.setEtat(etat);
        todo.setDate(date);
        return todo;
    }

    @Override
    public List<Todo> defaultsTodos()
    {
        List<Todo> todos = new LinkedList<>();

        // Todo 1
        Todo todo1 = createTodo("Todo.jsx", "Finir le Todo.jsx", 3, "2023-04-10");
        todos.add(todo1);

        // Todo 2
        Todo todo2 = createTodo("TodoApp.jsx", "Finir le TodoApp.jsx", 1, "2023-04-01");
        todos.add(todo2);

        // Todo 3
        Todo todo3 = createTodo("TodoFilters.jsx", "Faire le TodoFilters.jsx", 2, "2023-04-25");
        todos.add(todo3);

        // Todo 4
        Todo todo4 = createTodo("TodoForm.jsx", "Faire le TodoForm.jsx", 3, "2023-04-27");
        todos.add(todo4);

        // Todo 5
        Todo todo5 = createTodo("TodoList.jsx", "Finir le TodoForm.jsx", 2, "2023-04-09");
        todos.add(todo5);

        // Todo 6
        Todo todo6 = createTodo("TodoStats.jsx", "Faire et voir comment faire le TodoStats.jsx", 1,
                "2023-04-17");
        todos.add(todo6);

        // Todo 7
        Todo todo7 = createTodo("Todo.css", "Ajouter du style dans Todo.css", 2, "2023-04-17");
        todos.add(todo7);

        // Todo 8
        Todo todo8 = createTodo("TodoApp.css", "Ajouter du style dans TodoApp.css", 3, "2023-04-17");
        todos.add(todo8);

        return todoRepo.saveAll(todos);
    }

    @Override
    public Todo updateEtat(int id, int etat)
    {
        Todo todoVar = todoRepo.findById(id).get();
        todoVar.setEtat(etat);
        todoRepo.save(todoVar);
        return todoVar;
    }
}
