package com.example.todoapplication;

public class TodoModal {

    public String detail;
    public String todoDate;
    private int id;

    public String getdetail() {
        return detail;
    }

    public void setdetail(String detail) {
        this.detail = detail;
    }

    public String gettodoDate() { return todoDate; }

    public void settodoDate(String todoDate) {
        this.todoDate = todoDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // constructor
    public TodoModal(String detail, String todoDate) {
        this.detail = detail;
        this.todoDate = todoDate;
    }



}
