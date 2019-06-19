package com.vpp.todomvc.todomvc.controller;

import com.vpp.todomvc.todomvc.dao.TodoDaoDb;
import com.vpp.todomvc.todomvc.model.Todo;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {

    @Autowired
    TodoDaoDb todoDaoDb;

    private final String SUCCESS = "{\"success\":true}";

    @PostMapping("/addTodo")
    public String addTodo(@RequestParam("todo-title") String todoTitle) {
        Todo newTodo = Todo.create(todoTitle);
        todoDaoDb.add(newTodo);
        return SUCCESS;
    }

    @PostMapping("/list")
    public String list(@RequestParam("status") String status) throws JSONException {
        List<Todo> daos = todoDaoDb.ofStatus(status);
        JSONArray arr = new JSONArray();
        for (Todo dao : daos) {
            JSONObject jo = new JSONObject();
            jo.put("id", dao.getId());
            jo.put("title", dao.getTitle());
            jo.put("completed", dao.isComplete());
            arr.put(jo);
        }
        return arr.toString(2);
    }

    @DeleteMapping("/todos/completed")
    public String deleteCompleted() {
        todoDaoDb.removeCompleted();
        return SUCCESS;
    }

    @PutMapping("/todos/toggle_all")
    public String toggleAllStatus(@RequestParam("toggle-all") String toggle) {
        String complete = toggle;
        todoDaoDb.toggleAll(complete.equals("true"));
        return SUCCESS;
    }

    @DeleteMapping("/todos/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        todoDaoDb.remove(id);
        return SUCCESS;
    }

    @PutMapping("/todos/{id}")
    public String updateById(@PathVariable("id") Long id, @RequestParam("todo-title") String todoTitle) {
        todoDaoDb.update(id, todoTitle);
        return SUCCESS;
    }

    @GetMapping("/todos/{id}")
    public String findById(@PathVariable("id") Long id) {
        return todoDaoDb.find(id).getTitle();
    }

    @PutMapping("/todos/:id/toggle_status")
    public String toggleStatusById(@RequestParam("status") String status, @RequestParam("id") Long id) {
        boolean completed = status.equals("true");
        todoDaoDb.toggleStatus(id, completed);
        return SUCCESS;
    }

}
