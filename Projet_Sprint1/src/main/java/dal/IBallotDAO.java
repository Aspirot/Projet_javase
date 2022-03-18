package dal;

import bll.model.Ballot;

import java.util.List;

public interface IBallotDAO {
    void addBallot(Ballot ballot);
    List<Ballot> getAllBallots();
    //nous n'avons pas trouvez de update qui serait nécessaire pour les ballots
    void deleteBallot(int ballotId);
}
