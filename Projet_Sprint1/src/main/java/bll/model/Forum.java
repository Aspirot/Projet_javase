package bll.model;

import java.util.Date;

public class Forum {
    private static int AUTO_INCREMENT_ID = 0;

    private int id;
    private String title;
    private Date createdOn;

    public Forum(String title, Date createdOn) {
        this.id = AUTO_INCREMENT_ID++;
        this.title = title;
        this.createdOn = createdOn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
