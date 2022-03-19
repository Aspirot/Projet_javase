package bll.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Ballot {
    private static int AUTO_INCREMENT_ID = 1;

    private int id;
    private String title;
    private LocalDate start;
    private LocalDate end;
    private Boolean isPublic;
    private Boolean isAnonymous;
    private List<Candidate> candidates;
    private List<Elector> electors;
    private int forumId;
    private int ownerId;

    public Ballot(String title, LocalDate start, LocalDate end, Boolean isPublic, Boolean isAnonymous, int forumId, int ownerId) {
        this.id = AUTO_INCREMENT_ID++;
        this.title = title;
        this.start = start;
        this.end = end;
        this.isPublic = isPublic;
        this.isAnonymous = isAnonymous;
        this.candidates = new ArrayList<>();
        this.electors = new ArrayList<>();
        this.forumId = forumId;
        this.ownerId = ownerId;
    }

    public void addCandidate(Candidate candidate){
        this.candidates.add(candidate);
    }

    public void addElector(Elector elector){
        this.electors.add(elector);
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

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
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

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    public List<Elector> getElectors() {
        return electors;
    }

    public void setElectors(List<Elector> electors) {
        this.electors = electors;
    }

    public int getForumId() {
        return forumId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public String toString() {
        return "Ballot{" +
                "id=" + id +
                ",\n title='" + title + '\'' +
                ",\n start=" + start +
                ", end=" + end +
                ",\n isPublic=" + isPublic +
                ", isAnonymous=" + isAnonymous +
                ",\n candidates=" + candidates +
                ",\n electors=" + electors +
                ",\n forumId=" + forumId +
                ", ownerId=" + ownerId +
                '}';
    }
}
