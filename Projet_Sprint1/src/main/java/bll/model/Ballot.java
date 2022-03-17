package bll.model;

import java.util.Date;

public class Ballot {
    private static int AUTO_INCREMENT_ID = 0;

    private int id;
    private String title;
    private Date start;
    private Date end;
    private Boolean isPublic;
    private Boolean isAnonymous;

    public Ballot(String title, Date start, Date end, Boolean isPublic, Boolean isAnonymous) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.isPublic = isPublic;
        this.isAnonymous = isAnonymous;
        this.id = AUTO_INCREMENT_ID++;
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

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public Boolean getAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(Boolean anonymous) {
        isAnonymous = anonymous;
    }
}
