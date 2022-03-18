package dal;

import bll.model.Vote;

import java.util.ArrayList;
import java.util.List;

public class VoteDAO implements IVoteDAO{
    private List<Vote> votes;

    public VoteDAO(InMemoryRepository memoryRepository) {
        this.votes = new ArrayList<>();
        memoryRepository.getVotes().stream().forEach(v -> saveVote(v));
    }

    @Override
    public void saveVote(Vote vote) {
        this.votes.add(vote);
    }

    @Override
    public List<Vote> getAllVotes() {
        return this.votes;
    }

    @Override
    public void changeVote(int voteId, int newPollSubjectId) {
        this.votes.stream().filter(v -> v.getId()==voteId).findFirst().get().setPollSubjectId(newPollSubjectId);
    }

    @Override
    public void deleteVote(int voteId) {
        this.votes.remove(this.votes.stream().filter(v -> v.getId()==voteId).findFirst());
    }
}
