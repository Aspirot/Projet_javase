package bll.control;

import bll.model.Ballot;
import bll.model.Forum;
import dal.BallotDAO;
import dal.IBallotDAO;

import java.time.LocalDate;
import java.util.Date;

public class BallotController {
    private IBallotDAO ballotDAO;

    public BallotController(IBallotDAO ballotDAO){
        this.ballotDAO = ballotDAO;
    }

    public void createBallot(String title, LocalDate start, LocalDate end, Boolean isPublic, Boolean isAnonymous, int forumid, int ownerId){
        Ballot ballot = new Ballot(title,start,end,isPublic,isAnonymous,forumid,ownerId);
        this.ballotDAO.addBallot(ballot);
    }

    public int getNumberOfElectorsForBallotById(int ballotId){
        return this.ballotDAO.fetchBallotById(ballotId).get().getElectors().size();
    }

    public int findWinnerUsingLoneScan(int ballotId){
        return 1;
    }

    public int findWinnerUsingPolyScan(int ballotId){
        
    }

    public IBallotDAO getBallotDAO() {
        return ballotDAO;
    }
}
