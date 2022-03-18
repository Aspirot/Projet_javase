package bll.control;

import bll.model.Ballot;
import bll.model.Forum;
import dal.BallotDAO;
import dal.IBallotDAO;

import java.util.Date;

public class BallotController {
    private IBallotDAO ballotDAO;

    public BallotController(BallotDAO ballotDAO){
        this.ballotDAO = ballotDAO;
    }

    public void createBallot(String title, Date start, Date end, Boolean isPublic, Boolean isAnonymous, Forum forum, int ownerId){
        Ballot ballot = new Ballot(title,start,end,isPublic,isAnonymous,forum,ownerId);
        this.ballotDAO.addBallot(ballot);
    }

    public int getNumberOfElectorsForBallotById(int ballotId){
        return this.ballotDAO.fetchBallotById(ballotId).get().getElectors().size();
    }

    public int findWinnerUsingLoneScan(int ballotId){
        return 1;
    }

    public int findWinnerUsingPolyScan(int ballotId){
        return 1;
    }

    public IBallotDAO getBallotDAO() {
        return ballotDAO;
    }
}
