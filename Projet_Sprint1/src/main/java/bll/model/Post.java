package bll.model;

import java.time.LocalDate;

public class Post {
    private static int AUTO_INCREMENT_ID = 0;

    private int id;
    private LocalDate date;
    private String message;
    private int forumId;
    private int electorId;

    public Post(LocalDate date, String message, int forumId, int electorId) {
        this.id = AUTO_INCREMENT_ID++;
        this.date = date;
        this.message = message;
        this.forumId = forumId;
        this.electorId = electorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
