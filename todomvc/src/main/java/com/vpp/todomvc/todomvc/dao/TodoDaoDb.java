package com.vpp.todomvc.todomvc.dao;

import com.vpp.todomvc.todomvc.model.Status;
import com.vpp.todomvc.todomvc.model.Todo;
import com.vpp.todomvc.todomvc.model.TodoComparator;
import com.vpp.todomvc.todomvc.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TodoDaoDb {

    @Autowired
    TodoRepository todoRepository;

    public void add(Todo todo)
    {
        todoRepository.save(todo);
    }

    public Todo find(Long id) {
        ArrayList<Todo> todos = todoRepository.findTodoById(id);
        return todos.get(0);
    }

    public void update(Long id, String title) {
        if (todoRepository.findTodoById(id).size() != 0) {
            todoRepository.updateTitle(title, id);
        }
    }

    public List<Todo> ofStatus(String statusString) {
        ArrayList<Todo> todos;
        if (statusString.equals("")) todos = (ArrayList<Todo>) todoRepository.findAll();
        else todos = todoRepository.findTodoByStatusString(statusString);
        Collections.sort(todos, new TodoComparator());
        return todos;
    }


    public void remove(Long id) {
        if (todoRepository.findTodoById(id).size() != 0) {
            todoRepository.deleteTodo(id);
        }
    }

    public void removeCompleted() {
        List<Todo> completed = ofStatus("COMPLETE");
        for (Todo todo : completed) {
            remove(todo.getId());
        }
    }

    public void toggleStatus(Long id, boolean isComplete) {
        Todo t = find(id);
        if (t != null) {
            if (t.isComplete()) todoRepository.updateStatusToActive(t.getId());
            else todoRepository.updateStatusToComplete(t.getId());
        }

    }

    public void toggleAll(boolean complete) {
        List<Todo> all = all();
        for (Todo todo : all) {
            if (!todo.isComplete()) todoRepository.updateStatusToComplete(todo.getId());
        }
    }

    public List<Todo> all() {
        return todoRepository.findAll();
    }
}
