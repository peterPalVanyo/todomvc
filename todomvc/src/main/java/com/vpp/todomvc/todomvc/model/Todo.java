package com.vpp.todomvc.todomvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    private Todo(String title, String id, Status status) {
        this.title = title;
        this.id = Long.parseLong(id);
        this.status = status;
    }


    public static Todo create(String title) {
        _idCounter++;
        return new Todo(title, _idCounter, Status.ACTIVE);
    }

    public void checkStatusAndSetCompleted() {
        if (status.equals(Status.ACTIVE)) {
//            setCompleter(false);
        } else if (status.equals(Status.COMPLETE)) {
//            setCompleted(true);
        } else {
            // TODO error handling
        }
    }

}
