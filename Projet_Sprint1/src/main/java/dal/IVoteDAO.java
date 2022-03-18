package dal;

import bll.model.Vote;

import java.util.Date;
import java.util.List;

public interface IVoteDAO {
    void createVote(int pollId, int pollSubjectId, int electorId, Date when, int rank);
    List<Vote> getAllVotes();
    void changeVote(int voteId, int newPollSubjectId);
    void deleteVote(int voteId);
}
