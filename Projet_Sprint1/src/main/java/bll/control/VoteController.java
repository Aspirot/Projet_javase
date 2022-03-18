package bll.control;

import bll.model.Vote;
import dal.IVoteDAO;
import dal.VoteDAO;

import java.util.Date;

public class VoteController {
    private IVoteDAO voteDAO;

    public VoteController(VoteDAO voteDAO){
        this.voteDAO = voteDAO;
    }

    public void createVote(Date when, int rank, int pollId, int pollSubjectId, int electorId){
        Vote newVote = new Vote(when,rank,pollId,pollSubjectId,electorId);
        this.voteDAO.addVote(newVote);
    }

    public IVoteDAO getVoteDAO() {
        return this.voteDAO;
    }
}
