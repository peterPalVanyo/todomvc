package com.vpp.todomvc.todomvc;

import com.vpp.todomvc.todomvc.model.Todo;
import com.vpp.todomvc.todomvc.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class TodomvcApplication {

    @Autowired
    private TodoRepository todoRepository;

    public static void main(String[] args) {
        SpringApplication.run(TodomvcApplication.class, args);
    }


    @Bean
    @Profile("production")
    public CommandLineRunner init() {
        return args -> {
            todoRepository.save(Todo.create("doktorhoz menni"));

        };
    }
}
