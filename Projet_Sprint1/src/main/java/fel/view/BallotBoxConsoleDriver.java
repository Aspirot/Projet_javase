package fel.view;

import bll.control.BallotController;
import bll.control.CandidateController;
import bll.control.ElectorController;
import bll.control.VoteController;
import dal.*;

import java.time.LocalDate;

public class BallotBoxConsoleDriver {
    public static void main(String[] args) {
        run_usingInMemoryRepository();
    }

    private static void run_usingInMemoryRepository() {
        InMemoryRepository memoryRepository = new InMemoryRepository();
        IVoteDAO voteDAO = new VoteDAO(memoryRepository);
        IBallotDAO ballotDAO = new BallotDAO(memoryRepository);
        ICandidateDAO candidateDAO = new CandidateDAO(memoryRepository);
        IElectorDAO electorDAO = new ElectorDAO(memoryRepository);

        VoteController voteController = new VoteController(voteDAO);
        BallotController ballotController = new BallotController(ballotDAO);
        CandidateController candidateController = new CandidateController(candidateDAO);
        ElectorController electorController = new ElectorController(electorDAO);


        /**
         * Following lines in argument were used as tests
         */
        //BallotController.getBallotDAO().fetchBallotById(2).stream().forEach(System.out::println);

        //System.out.println("Number of electors for ballot 2 was : " + ballotController.getNumberOfElectorsForBallotById(2));

        //Create an elector and add him to an election
        electorController.createElector("Denis","Aspirot",1,"denisaspicot@gmail.com");
        electorController.addElectorToElection(11,2);

        //Show difference from line 35 (after adding an elector)
        //System.out.println("Number of electors for ballot 2 is : " + ballotController.getNumberOfElectorsForBallotById(2));

        //Create votes for the previously added elector
        voteController.createVote(LocalDate.now(),1,2,1,11);
        voteController.createVote(LocalDate.now(),2,2,2,11);

        //Show difference from line 33
        //BallotController.getBallotDAO().fetchBallotById(2).stream().forEach(System.out::println);

        //findNumberOfInFavorByCandidate
        System.out.println("Total number of electors in favor of candidate 1 as rank 1 in poll 1 is : " + candidateController.findNumberOfInFavorByCandidate(1,1,1));

        //Ballot creation
        //ballotController.createBallot("Tomorrow poll",LocalDate.now(),LocalDate.now().plusDays(2),false,true,4,11);
        //System.out.println("Ballot 4 : \n" + BallotController.getBallotDAO().fetchBallotById(4));

        System.out.println("Winner of ballot 1 with Lone-Scan is : " + ballotController.findWinnerUsingLoneScan(1));
        System.out.println("Winner of ballot 2 with Lone-Scan is : " + ballotController.findWinnerUsingLoneScan(2));
        System.out.println("Winner of ballot 3 with Lone-Scan is : " + ballotController.findWinnerUsingLoneScan(3));
        VoteController.getVoteDAO().getAllVotes().stream().forEach(System.out::println);
        System.out.println("Winner of ballot 1 with Poly-Scan is : " + ballotController.findWinnerUsingPolyScan(1));
        System.out.println("Winner of ballot 2 with Poly-Scan is : ");
        System.out.println("Winner of ballot 3 with Poly-Scan is : ");


    }
}
