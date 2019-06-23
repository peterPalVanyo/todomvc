package com.vpp.todomvc.todomvc;

import com.vpp.todomvc.todomvc.model.AppUser;
import com.vpp.todomvc.todomvc.model.Todo;
import com.vpp.todomvc.todomvc.repository.TodoRepository;
import com.vpp.todomvc.todomvc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class TodomvcApplication {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    public static void main(String[] args) {
        SpringApplication.run(TodomvcApplication.class, args);
    }


    @Bean
    @Profile("production")
    public CommandLineRunner init() {

        return args -> {
            todoRepository.save(Todo.create("doktorhoz menni"));
            userRepository.deleteAll();
            userRepository.save(AppUser.builder().username("alma").password(passwordEncoder.encode("alma")).roles(Arrays.asList( "ROLE_USER")).build());
            userRepository.save(AppUser.builder().username("bela").password(passwordEncoder.encode("bela")).roles(Arrays.asList( "ROLE_ADMIN")).build());
        };
    }
}
