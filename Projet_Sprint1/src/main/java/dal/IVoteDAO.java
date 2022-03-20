package dal;

import bll.model.Elector;
import bll.model.Vote;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IVoteDAO {
    void addVote(Vote vote);
    List<Vote> getAllVotes();
    List<Vote> getAllVotesForBallot(int pollId);
    Optional<Vote> fetchVoteById(int voteId);
    Optional<Vote> fetchVoteByCandidateId_PollId_Rank(int candidateId,int pollId,int rank);
    Optional<Vote> fetchVoteByElectorId_PollId_Rank(int electorId,int pollId,int rank);
    void deleteVote(int voteId);
}
