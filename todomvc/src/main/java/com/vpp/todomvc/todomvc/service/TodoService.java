package com.vpp.todomvc.todomvc.service;

import com.vpp.todomvc.todomvc.model.Status;
import com.vpp.todomvc.todomvc.model.Todo;
import com.vpp.todomvc.todomvc.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {

    @Autowired
    TodoRepository todoRepository;


    // List by id
    public List<Todo> listByStatus(String status) {
        List<Todo> allTodo = todoRepository.findAll();

        for (Todo todo : allTodo) {
            todo.checkStatusAndSetCompleted();
        }

        if (status.equals("active")) {
            List<Todo> activeTodo = new ArrayList<>();
            for (Todo todo : allTodo) {
                if (todo.getStatus().equals(Status.ACTIVE)) {
                    activeTodo.add(todo);
                }
            }
            return activeTodo;
        }
        if (status.equals("complete")) {
            List<Todo> completeTodo = new ArrayList<>();
            for (Todo todo : allTodo) {
                if (todo.getStatus().equals(Status.COMPLETE)) {
                    completeTodo.add(todo);
                }
            }
            return completeTodo;
        }
        return allTodo;
    }
}
