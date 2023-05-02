package com.todolist.backend_todolist.controller;

import com.todolist.backend_todolist.entity.Todo;
import com.todolist.backend_todolist.service.SequenceGeneratorService;
import com.todolist.backend_todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController
{
    @Autowired
    private TodoService todoService;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @GetMapping("/all")
    public List<Todo> getTodos()
    {
        // On recupère toutes les taches.
        return todoService.getTodos();
    }

    @PostMapping("/insert")
    public Todo insert(@RequestBody Todo todo)  
    {
        // On change l'id part l'id suivant.
        todo.setId(sequenceGeneratorService.getSequenceNumber(Todo.SEQUENCE_NAME));
        return todoService.addTodo(todo);
    }

    @DeleteMapping("/deleteAll")
    public List<Todo> deleteAll()
    {
        // On met le debut de l'id à 1.
        sequenceGeneratorService.setStartSequenceNumber(Todo.SEQUENCE_NAME);

        // On supprime toutes les taches de la table.
        return todoService.deleteTodos();
    }

    @PostMapping("/setDefaults")
    public List<Todo> setDefaults()
    {
        // On crée des taches par default et on les ajoute dans la table.
        return todoService.defaultsTodos();
    }

    @PutMapping("/updateEtat/{id}/{etat}")
    public Todo updateEtat(@PathVariable int id, @PathVariable int etat)
    {
        // On modifie l'etat de la tache.
        return todoService.updateEtat(id, etat);
    }
}
