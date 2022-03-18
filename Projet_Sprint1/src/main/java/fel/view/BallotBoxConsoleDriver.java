package fel.view;

import dal.IVoteDAO;
import dal.InMemoryRepository;
import dal.VoteDAO;

public class BallotBoxConsoleDriver {
    public static void main(String[] args) {
        run_usingInMemoryRepository();
    }

    private static void run_usingInMemoryRepository() {
        InMemoryRepository memoryRepository = new InMemoryRepository();
        IVoteDAO voteDAO = new VoteDAO(memoryRepository);
    }
}
