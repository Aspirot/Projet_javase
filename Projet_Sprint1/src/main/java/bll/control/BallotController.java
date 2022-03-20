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
import java.util.*;

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

    public int findWinnerUsingPolyScan(int ballotId){

        /*
        Ballot ballot = ballotDAO.fetchBallotById(ballotId).get();
        int winner = -1;
        int size = VoteController.getVoteDAO().getAllVotes().size();

        int lowestValue = 100;
        int lowestId = -1;

        Map<Integer,Integer> currentCandidate_Votes = new HashMap<>();
        Map<Integer,Integer> losingElectorId_Rank = new HashMap<>();

        for (Candidate candidate:ballot.getCandidates()) {
            currentCandidate_Votes.put(candidate.getId(),CandidateController.findNumberOfInFavorByCandidate(candidate.getId(),ballotId,1));
        }
        while (winner==-1||currentCandidate_Votes.size()>1){

            for (var kv: currentCandidate_Votes.entrySet()){
                if(size/2<=kv.getValue()){
                    winner=kv.getKey();
                }
            }

            for (var kv: currentCandidate_Votes.entrySet()){
                if(kv.getValue()<lowestValue){
                    lowestValue=kv.getValue();
                    lowestId=kv.getKey();
                }
            }
            lowestValue = -1;
            currentCandidate_Votes.remove(lowestId);

            losingElectorId_Rank.put(VoteController.getVoteDAO().fetchVoteByCandidateId_PollId_Rank(lowestId-1,ballotId,1).get().getElectorId(),2);

            for (var kv:losingElectorId_Rank.entrySet()) {
                for (var kv2: currentCandidate_Votes.entrySet()){
                    //get vote id to fetch elector
                    if(kv2.getKey()==VoteController.getVoteDAO().fetchVoteByElectorId_PollId_Rank(kv.getKey(),ballotId,kv.getValue()).get().getPollSubjectId())
                        kv2.setValue(kv2.getValue()+1);
                }
            }

        }

        int numbElectors = ballotDAO.fetchBallotById(ballotId).get().getElectors().size();
        List<Integer> candidates = ballotDAO.fetchBallotById(ballotId).get().getCandidates().stream().map(c -> c.getId()).toList();
        for (int i = 0; i < numbElectors; i++) {

        }

        int losing=0;
        Candidate loser = null;
        List<Candidate> polylist = ballotDAO.fetchBallotById(ballotId).get().getCandidates();
        removeLastPlace(polylist, losing, loser);*/

        int numberOfOptions= 0;
        List<Candidate> polylist = ballotDAO.fetchBallotById(ballotId).get().getCandidates();
        int winnerPoint= 1000000000;
        int winner=-1;
        for(int i=0; i<polylist.size();i++)
        {
            numberOfOptions++;
        }
        for (Candidate c : polylist)
        {

            int currentCandidatePoints = 0;
            for(Vote vote: VoteController.getVoteDAO().getAllVotes().stream().toList())
            {
                if(c.getId()==vote.getPollSubjectId())
                {
                    //manque une façon de donner le nombre de point=nombre options, quand rank=1 points = nomber option et point descend quand rank monte
                    for(int r=1; r<numberOfOptions;r++)
                    {

                        if(vote.getRank()==r)
                        {
                         currentCandidatePoints=currentCandidatePoints+r;
                        }
                    }
                }
            }
            //en ordre descendant, c scuff, see line 103, problem steems from line 118
            if(winnerPoint>currentCandidatePoints)
            {
                winnerPoint=currentCandidatePoints;
                winner=c.getId();
            }
        }

        return winner;
    }

    public static IBallotDAO getBallotDAO() {
        return ballotDAO;
    }

    /*public void removeLastPlace(List<Candidate> polylist, int losing, Candidate loser){
        Candidate winner;
        for(Candidate candidate:polylist){

            int currentCandidateVotes =0;
            for(Vote vote: VoteController.getVoteDAO().getAllVotes().stream().filter(v->v.getRank()== 1).toList())
            {
                if(candidate.getId()==vote.getPollSubjectId())
                {
                    currentCandidateVotes++;
                }
            }
            if(losing>=currentCandidateVotes)
            {
                losing=currentCandidateVotes;
                loser=candidate;
            }

            if(currentCandidateVotes>=VoteController.getVoteDAO().getAllVotes().size()/2)
            {
                winner = candidate;
            }



            polylist.remove(loser);
        }

        return winner;
    }*/

}
