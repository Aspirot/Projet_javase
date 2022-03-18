package bll.control;

import bll.model.Ballot;
import bll.model.Candidate;
import dal.BallotDAO;
import dal.CandidateDAO;
import dal.ICandidateDAO;

public class CandidateController {
    private ICandidateDAO candidateDAO;

    public void addCanditateToElection(int canditateId, int pollId, BallotController ballotController)
    {
        BallotController.getBallotDAO().fetchBallotById(pollId).get().add(candidateDAO.getAllCanditates().stream().filter(c -> c.getId()==canditateId).findFirst());
    }

    public CandidateController(CandidateDAO candidateDAO){
        this.candidateDAO = candidateDAO;
    }
}
