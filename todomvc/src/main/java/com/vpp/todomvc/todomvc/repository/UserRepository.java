package com.vpp.todomvc.todomvc.repository;

import com.vpp.todomvc.todomvc.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Integer> {
    AppUser findByUsername(String username);
}
