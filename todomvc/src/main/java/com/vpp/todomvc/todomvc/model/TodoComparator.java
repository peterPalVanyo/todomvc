package com.vpp.todomvc.todomvc.model;

import java.util.Comparator;

public class TodoComparator implements Comparator<Todo> {

    @Override
    public int compare(Todo o1, Todo o2) {
        if (o1.getId().equals(o2.getId())) return 0;
        else if(o1.getId().compareTo(o2.getId()) > 0) return 1;
        else return -1;
    }
}

