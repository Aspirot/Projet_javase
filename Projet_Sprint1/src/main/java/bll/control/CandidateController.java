package bll.control;

import bll.model.Ballot;
import bll.model.Candidate;
import dal.BallotDAO;
import dal.CandidateDAO;
import dal.ICandidateDAO;

public class CandidateController {
    private static ICandidateDAO candidateDAO;

    public CandidateController(ICandidateDAO candidateDAO){
        this.candidateDAO = candidateDAO;
    }

    //added, not used
    public void addCandidateToElection(int candidateId, int pollId)
    {
        BallotController.getBallotDAO().fetchBallotById(pollId).get().addCandidate(candidateDAO.getAllCanditates().stream().filter(c -> c.getId()==candidateId).findFirst().get());
    }

    public static int findNumberOfInFavorByCandidate(int candidateId,int pollId,int rank){
        return VoteController.getVoteDAO().getAllVotes().stream().filter(v -> v.getPollSubjectId()==candidateId&&v.getRank()==rank&&v.getPollId()==pollId).toList().size();
    }

    public static ICandidateDAO getCandidateDAO() {
        return candidateDAO;
    }
}
