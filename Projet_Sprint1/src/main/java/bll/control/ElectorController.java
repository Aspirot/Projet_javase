package bll.control;

import bll.model.Ballot;
import bll.model.Elector;
import dal.ElectorDAO;
import dal.IElectorDAO;

import java.util.List;

public class ElectorController {
    private IElectorDAO electorDAO;

    public ElectorController(ElectorDAO electorDAO){
        this.electorDAO = electorDAO;
    }

    public void createElector(String login, String password, int weight, String email){
        Elector newElector = new Elector(login,password,weight,email);
        this.electorDAO.addElector(newElector);
    }

    public /*BallotController*/void addElectorToElection(int electorId, int pollId, BallotController ballotController){
        ballotController.getBallotDAO().fetchBallotById(pollId).get().addElector(this.electorDAO.fetchElectorById(electorId).get());
        /*return ballotController;*/
    }
}
