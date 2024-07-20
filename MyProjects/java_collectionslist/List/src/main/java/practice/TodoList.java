package practice;

import java.util.ArrayList;

public class TodoList {
    private ArrayList<String> todoList = new ArrayList<>();
    public void add(String todo) {
        todoList.add(todo);
    }

    public void add(int index, String todo) {
        if (index > todoList.size()) {add(todo);}
        else {todoList.add(index, todo);}
    }

    public void edit(int index, String todo) {
        if (index < todoList.size()) {
            todoList.set(index, todo);
        }
    }

    public void delete(int index) {
        if (index < todoList.size()) {
            todoList.remove(index);
        }
    }

    public ArrayList<String> getTodos() {
        return new ArrayList<>(todoList);
    }
}