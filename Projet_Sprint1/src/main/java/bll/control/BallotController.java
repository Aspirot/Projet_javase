package bll.control;

import bll.model.*;
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


        Ballot ballot = ballotDAO.fetchBallotById(ballotId).get();
        int winner = -1;
        int size = VoteController.getVoteDAO().getAllVotesForBallot(ballotId).size();

        int lowestValue = 100;
        int lowestId = -1;

        Map<Integer,Integer> currentCandidate_Votes = new HashMap<>();
        Map<Integer,Integer> losingElectorId_Rank = new HashMap<>();

        for (Candidate candidate:ballot.getCandidates()) {
            currentCandidate_Votes.put(candidate.getId(),CandidateController.findNumberOfInFavorByCandidate(candidate.getId(),ballotId,1));
        }
        while (winner==-1||currentCandidate_Votes.size()>1){

            for (var kv: currentCandidate_Votes.entrySet()){
                if(kv.getValue()>size/2){
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

        /*
        int numbElectors = ballotDAO.fetchBallotById(ballotId).get().getElectors().size();
        int numbVotes = VoteController.getVoteDAO().getAllVotesForBallot(ballotId).size();
        //map of <candidateId,numberOfOccurences>
        Map<Integer,Integer> candidates = new HashMap<>();
        for (int id:ballotDAO.fetchBallotById(ballotId).get().getCandidates().stream().map(c -> c.getId()).toList()) {
            candidates.put(id,0);
        }


        int winner = 0;
        int loser = 0;
        int winnerCount = 0;
        int loserCount = numbVotes+1;
        int turn = 1;

        //initialises candidates values
        for (int i = 0; i < numbElectors; i++) {
            if(turn==1) {
                int currentElectorId = ballotDAO.fetchBallotById(ballotId).get().getElectors().get(i).getId();
                int currentChoice = VoteController.getVoteDAO().fetchVoteByElectorId_PollId_Rank(currentElectorId, ballotId, 1).get().getPollSubjectId();
                for (var kv : candidates.entrySet()) {
                    if (kv.getKey() == currentChoice)
                        kv.setValue(kv.getValue() + 1);
                }
            }
        }*/




        //ElectorId<rank,pollsubjectId>
        /*
        Map<Elector,List<Vote>> electorId_rank_candidateId = new HashMap<>();
        for (Elector e:ballotDAO.fetchBallotById(ballotId).get().getElectors()) {
            electorId_rank_candidateId.put(e,VoteController.getVoteDAO().getAllVotesForBallot(ballotId).stream().filter(el -> el.getElectorId()==e.getId()).toList());
        }
        electorId_rank_candidateId.values().stream().sorted();

        Candidate top = null;
        Candidate bottom = null;
        int topCounter = 0;
        int bottomCounter = 100;
        while (top==null){
            for (var kv:electorId_rank_candidateId.entrySet()) {
                if(kv.getValue().stream().findFirst().get().get<bottomCounter)
            }
        }*/

























        return winner;
    }

    public static IBallotDAO getBallotDAO() {
        return ballotDAO;
    }
}
