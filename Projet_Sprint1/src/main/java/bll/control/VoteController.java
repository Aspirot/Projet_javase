package bll.control;

import bll.model.Vote;
import dal.IVoteDAO;

import java.time.LocalDate;

public class VoteController {
    private static IVoteDAO voteDAO;

    public VoteController(IVoteDAO voteDAO){
        this.voteDAO = voteDAO;
    }

    public void createVote(LocalDate when, int rank, int pollId, int pollSubjectId, int electorId){
        Vote newVote = new Vote(when,rank,pollId,pollSubjectId,electorId);
        voteDAO.addVote(newVote);
    }

    public static IVoteDAO getVoteDAO() {
        return voteDAO;
    }
}
