package com.vpp.todomvc.todomvc.repository;

import com.vpp.todomvc.todomvc.model.Status;
import com.vpp.todomvc.todomvc.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Controller
public interface TodoRepository extends JpaRepository<Todo, String> {

    @Query("SELECT t FROM Todo t WHERE t.id = :id")
    ArrayList<Todo> findTodoById(@Param("id") Long id);

    @Query("SELECT t FROM Todo t WHERE t.status = :status")
    ArrayList<Todo> findTodoByStatus(@Param("status") Status status);

    @Query("SELECT t FROM Todo t WHERE t.status = :statusstring")
    ArrayList<Todo> findTodoByStatusString(@Param("statusstring") String statusstring);

    //modifyings, transactional

    @Modifying
    @Query("DELETE FROM Todo t WHERE t.id = :id")
    @Transactional
    void deleteTodo(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Todo t SET t.title = :title WHERE t.id = :id")
    @Transactional
    void updateTitle(@Param("title") String title, @Param("id") Long id);
}
