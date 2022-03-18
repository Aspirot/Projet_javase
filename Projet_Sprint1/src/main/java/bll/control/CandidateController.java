package bll.control;

import dal.CandidateDAO;
import dal.ICandidateDAO;

public class CandidateController {
    private ICandidateDAO candidateDAO;

    public CandidateController(CandidateDAO candidateDAO){
        this.candidateDAO = candidateDAO;
    }
}
