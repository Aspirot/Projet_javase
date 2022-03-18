package bll.control;

import bll.model.Ballot;
import bll.model.Candidate;
import dal.BallotDAO;
import dal.CandidateDAO;
import dal.ICandidateDAO;

public class CandidateController {
    private ICandidateDAO candidateDAO;

    public void addCandidateToElection(int candidateId, int pollId, BallotController ballotController)
    {
        ballotController.getBallotDAO().fetchBallotById(pollId).get().addCandidate(candidateDAO.getAllCanditates().stream().filter(c -> c.getId()==candidateId).findFirst().get());
    }

    public CandidateController(CandidateDAO candidateDAO){
        this.candidateDAO = candidateDAO;
    }
}
