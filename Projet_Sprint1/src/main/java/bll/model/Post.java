package bll.model;

import java.util.Date;

public class Post {
    private static int AUTO_INCREMENT_ID = 0;

    private int id;
    private Date date;
    private String message;

    public Post( Date date, String message) {
        this.id = AUTO_INCREMENT_ID++;
        this.date = date;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
