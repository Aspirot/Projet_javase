package dal;

import bll.model.Candidate;

import java.util.List;

public interface ICandidateDAO {
    void savecandidate(Candidate candidate);
    List<Candidate> getAllCanditates();
    void deleteCanditate(int candidateId);

}
