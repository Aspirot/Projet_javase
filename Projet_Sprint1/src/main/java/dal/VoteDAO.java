package dal;

import bll.model.Vote;

import java.util.Date;
import java.util.List;

public class VoteDAO implements IVoteDAO{
    @Override
    public void createVote(int pollId, int pollSubjectId, int electorId, Date when, int rank) {
        
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
