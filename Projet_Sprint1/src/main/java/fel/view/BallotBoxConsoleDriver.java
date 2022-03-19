package fel.view;

import bll.control.BallotController;
import bll.control.CandidateController;
import bll.control.ElectorController;
import bll.control.VoteController;
import bll.model.Vote;
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

        System.out.println(electorDAO.fetchElectorById(3).get().toString());
        /* Testing DAO CRUD methods
        voteDAO.addVote(new Vote(LocalDate.now(),1,3,4,10));
        voteDAO.getAllVotes().stream().forEach(System.out::println);
        voteDAO.deleteVote(10);
        voteDAO.changeVote(11,3);*/
        voteDAO.getAllVotes().stream().forEach(System.out::println);

        VoteController voteController = new VoteController(voteDAO);
        BallotController ballotController = new BallotController(ballotDAO);
        CandidateController candidateController = new CandidateController(candidateDAO);
        ElectorController electorController = new ElectorController(electorDAO);
    }
}
