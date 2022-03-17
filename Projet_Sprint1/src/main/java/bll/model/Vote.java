package bll.model;

import java.util.Date;

public class Vote {
    private static int AUTO_INCREMENT_ID = 0;

    private int id;
    private Date when;
    private int rank;

    public Vote(Date when, int rank) {
        this.id = AUTO_INCREMENT_ID++;
        this.when = when;
        this.rank = rank;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getWhen() {
        return when;
    }

    public void setWhen(Date when) {
        this.when = when;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
