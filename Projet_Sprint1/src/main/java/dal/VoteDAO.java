package dal;

import bll.model.Vote;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VoteDAO implements IVoteDAO{
    private List<Vote> votes;

    public VoteDAO() {
        votes = new ArrayList<>();
    }

    @Override
    public void saveVote(Vote vote) {
        votes.add(vote);
    }

    @Override
    public List<Vote> getAllVotes() {
        return null;
    }

    @Override
    public void changeVote(int voteId, int newPollSubjectId) {

    }

    @Override
    public void deleteVote(int voteId) {

    }
}
