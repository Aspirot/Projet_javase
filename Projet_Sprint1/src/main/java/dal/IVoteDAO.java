package dal;

import java.util.Date;

public interface IVoteDAO {
    void createVote(int pollId, int pollSubjectId, int electorId, Date when, int rank);
}
