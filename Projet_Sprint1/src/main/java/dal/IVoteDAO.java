package dal;

import bll.model.Elector;
import bll.model.Vote;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IVoteDAO {
    void addVote(Vote vote);
    List<Vote> getAllVotes();
    Optional<Vote> fetchElectorById(int voteId);
    void deleteVote(int voteId);
}
