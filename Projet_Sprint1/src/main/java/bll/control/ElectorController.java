package bll.control;

import bll.model.Ballot;
import bll.model.Elector;
import dal.ElectorDAO;
import dal.IElectorDAO;

import java.util.List;

public class ElectorController {
    private IElectorDAO electorDAO;

    public ElectorController(IElectorDAO electorDAO){
        this.electorDAO = electorDAO;
    }

    public void createElector(String login, String password, int weight, String email){
        Elector newElector = new Elector(login,password,weight,email);
        this.electorDAO.addElector(newElector);
    }

    public void addElectorToElection(int electorId, int pollId){
        BallotController.getBallotDAO().fetchBallotById(pollId).get().addElector(this.electorDAO.fetchElectorById(electorId).get());
    }
}
