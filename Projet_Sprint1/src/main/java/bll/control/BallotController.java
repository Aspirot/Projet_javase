package bll.control;

import bll.model.Ballot;
import bll.model.Candidate;
import bll.model.Forum;
import bll.model.Vote;
import dal.BallotDAO;
import dal.CandidateDAO;
import dal.IBallotDAO;
import dal.VoteDAO;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class BallotController {
    private static IBallotDAO ballotDAO;

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
        List<Vote> allRank1Votes = VoteController.getVoteDAO().getAllVotes().stream().filter(v -> v.getPollId()==ballotId).toList().stream().filter(v -> v.getRank()==1).toList();
        int winner = -1;
        int winnerNumb = -1;
        for (Candidate candidate:ballotDAO.fetchBallotById(ballotId).get().getCandidates()) {
            int currentCand = allRank1Votes.stream().filter(v -> v.getPollSubjectId()==candidate.getId()).toList().size();
            if(winnerNumb<currentCand){
                winner=candidate.getId();
                winnerNumb = currentCand;
            }
        }
        return winner;
    }

    public int findWinnerUsingPolyScan(int ballotId, VoteController voteController){
        int losing=0;
        int turn= 1;
        Candidate loser = null;
        List<Candidate> polylist = ballotDAO.fetchBallotById(ballotId).get().getCandidates();
        removeLastPlace(polylist, voteController, turn, losing, loser);


        return losing;
    }

    public static IBallotDAO getBallotDAO() {
        return ballotDAO;
    }

    public void removeLastPlace(List<Candidate> polylist, VoteController voteController, int turn, int losing, Candidate loser){
        for(Candidate canditate:polylist){

            int currentCandidateVotes =0;
            int finalTurn = turn;
            for(Vote vote: voteController.getVoteDAO().getAllVotes().stream().filter(v->v.getRank()== finalTurn).toList())
            {
                if(canditate.getId()==vote.getPollSubjectId())
                {
                    currentCandidateVotes++;
                }
            }
            if(losing>=currentCandidateVotes)
            {
                losing=currentCandidateVotes;
                loser=canditate;

            }
            turn++;
            polylist.remove(loser);
        }
    }
}
