package com.vpp.todomvc.todomvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Todo {

    private String title;

    @Id
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    private static Long _idCounter = 0L;

    public boolean isComplete() {
        return this.status == Status.COMPLETE;
    }

    public static Todo create(String title) {
        _idCounter++;
        return new Todo(title, _idCounter, Status.ACTIVE);
    }

}
