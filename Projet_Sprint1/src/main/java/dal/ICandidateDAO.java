package dal;

import bll.model.Candidate;
import bll.model.Elector;

import java.util.List;
import java.util.Optional;

public interface ICandidateDAO {
    void savecandidate(Candidate candidate);
    List<Candidate> getAllCanditates();
    Optional<Candidate> fetchCandidateById(int candidateId);
    void deleteCanditate(int candidateId);
}
