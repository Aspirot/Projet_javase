package dal;

import bll.model.Elector;
import bll.model.Vote;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VoteDAO implements IVoteDAO{
    private List<Vote> votes;

    public VoteDAO(InMemoryRepository memoryRepository) {
        this.votes = new ArrayList<>();
        memoryRepository.getVotes().stream().forEach(v -> addVote(v));
    }

    @Override
    public void addVote(Vote vote) {
        this.votes.add(vote);
    }

    @Override
    public List<Vote> getAllVotes() {
        return this.votes;
    }

    @Override
    public Optional<Vote> fetchVoteById(int voteId) {
        return this.votes.stream().filter(v -> v.getId()==voteId).findFirst();
    }

    @Override
    public Optional<Vote> fetchVoteByCandidateId_PollId_Rank(int candidateId, int pollId, int rank) {
        return this.votes.stream().filter(v -> v.getPollSubjectId()==candidateId&&v.getPollId()==pollId&&v.getRank()==rank).findFirst();
    }

    @Override
    public Optional<Vote> fetchVoteByElectorId_PollId_Rank(int electorId, int pollId, int rank) {
        return this.votes.stream().filter(v -> v.getElectorId()==electorId&&v.getPollId()==pollId&&v.getRank()==rank).findFirst();
    }


    @Override
    public void deleteVote(int voteId) {
        this.votes.remove(this.votes.stream().filter(v -> v.getId()==voteId).findFirst().get());
    }
}
