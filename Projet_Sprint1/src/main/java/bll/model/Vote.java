package bll.model;

import java.util.Date;

public class Vote {
    private static int AUTO_INCREMENT_ID = 0;

    private int id;
    private Date when;
    private int rank;
    private int pollId;
    private int pollSubjectId;
    private int electorId;

    public Vote(Date when, int rank, int pollId, int pollSubjectId, int electorId) {
        this.id = AUTO_INCREMENT_ID++;
        this.when = when;
        this.rank = rank;
        this.pollId = pollId;
        this.pollSubjectId = pollSubjectId;
        this.electorId = electorId;
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

    public int getPollId() {
        return pollId;
    }

    public int getPollSubjectId() {
        return pollSubjectId;
    }

    public int getElectorId() {
        return electorId;
    }
}
