package main.model;

import java.sql.Date;

public class TaskUpdateRequest {

    private String name;
    private String text;
    private String deadline;

    public void setName(String name) {
        this.name = name;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public String getDeadline() {
        return deadline;
    }
}
