package dal;

import bll.model.*;
import net.andreinc.mockneat.MockNeat;

import java.util.ArrayList;
import java.util.List;

public class InMemoryRepository {

    private List<Vote> votes;
    private List<Candidate> candidates;
    private List<Elector> electors;
    private List<Ballot> ballots;
    private List<Post> posts;
    private List<Forum> forums;

    public InMemoryRepository(){
        this.ballots = new ArrayList<>();
        this.candidates = new ArrayList<>();
        this.electors = new ArrayList<>();
        this.forums = new ArrayList<>();
        this.posts = new ArrayList<>();
        this.votes = new ArrayList<>();

        MockNeat mock = MockNeat.threadLocal();
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public List<Elector> getElectors() {
        return electors;
    }

    public List<Ballot> getBallots() {
        return ballots;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public List<Forum> getForums() {
        return forums;
    }
}
