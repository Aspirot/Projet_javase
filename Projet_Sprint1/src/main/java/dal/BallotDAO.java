package dal;

import bll.model.Ballot;

import java.util.ArrayList;
import java.util.List;

public class BallotDAO implements IBallotDAO{
    List<Ballot> ballots;//polls

    BallotDAO(InMemoryRepository memoryRepository){
        ballots = new ArrayList<>();
        memoryRepository.getBallots().stream().forEach(b -> addBallot(b));
    }
    @Override
    public void addBallot(Ballot ballot) {
        this.ballots.add(ballot);
    }

    @Override
    public List<Ballot> getAllBallots() {
        return this.ballots;
    }

    @Override
    public void deleteBallot(int ballotId) {
        this.ballots.remove(this.ballots.stream().filter(b -> b.getId()==ballotId).findFirst());
    }
}
