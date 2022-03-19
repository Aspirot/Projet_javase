package bll.control;

import bll.model.Ballot;
import bll.model.Candidate;
import bll.model.Forum;
import bll.model.Vote;
import dal.BallotDAO;
import dal.IBallotDAO;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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

    public int findWinnerUsingLoneScan(int ballotId, VoteController voteController){
        List<Vote> allRank1Votes = voteController.getVoteDAO().getAllVotes().stream().filter(v -> v.getPollId()==ballotId).toList().stream().filter(v -> v.getRank()==1).toList();
        int winner = -1;
        for (Candidate candidate:ballotDAO.fetchBallotById(ballotId).get().getCandidates()) {
            int currentCandidateVotes = 0;
            for (Vote vote:allRank1Votes) {
                if(candidate.getId()==vote.getPollSubjectId()){
                    currentCandidateVotes++;
                }
            }
            if(winner<currentCandidateVotes)
                winner = currentCandidateVotes;
        }
        return winner;
    }

    public int findWinnerUsingPolyScan(int ballotId){
        
    }

    public IBallotDAO getBallotDAO() {
        return ballotDAO;
    }
}
