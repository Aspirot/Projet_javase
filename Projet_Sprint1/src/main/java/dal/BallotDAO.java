package dal;

import bll.model.Ballot;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BallotDAO implements IBallotDAO{
    List<Ballot> ballots;//polls

    public BallotDAO(InMemoryRepository memoryRepository){
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
    public Optional<Ballot> fetchBallotById(int ballotId) {
        return this.ballots.stream().filter(b -> b.getId()==ballotId).findFirst();
    }

    @Override
    public void deleteBallot(int ballotId) {
        this.ballots.remove(this.ballots.stream().filter(b -> b.getId()==ballotId).findFirst());
    }
}
