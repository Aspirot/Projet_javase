package dal;

import bll.model.*;
import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnit;
import net.andreinc.mockneat.abstraction.MockUnitInt;

import java.util.*;

import static net.andreinc.mockneat.unit.objects.Constructor.constructor;
import static net.andreinc.mockneat.unit.seq.IntSeq.intSeq;
import static net.andreinc.mockneat.unit.text.Strings.strings;
import static net.andreinc.mockneat.unit.time.LocalDates.localDates;
import static net.andreinc.mockneat.unit.types.Bools.bools;
import static net.andreinc.mockneat.unit.types.Ints.ints;
import static net.andreinc.mockneat.unit.user.Emails.emails;
import static net.andreinc.mockneat.unit.user.Names.names;
import static net.andreinc.mockneat.unit.user.Passwords.passwords;


public class InMemoryRepository {

    private List<Vote> votes;
    private List<Candidate> candidates;
    private List<Elector> electors;
    private List<Ballot> ballots;
    private List<Post> posts;
    private List<Forum> forums;

    public InMemoryRepository(){
        MockUnitInt generatorTo3 = ints().range(1, 4);
        MockUnitInt generatorTo10 = ints().range(1, 11);

        MockUnit<Forum> forumGenerator =
                constructor(Forum.class).params(
                        strings(),
                        localDates()
                );
        MockUnit<Post> postGenerator =
                constructor(Post.class).params(
                        localDates(),
                        strings(),
                        generatorTo3,
                        generatorTo10
                );
        MockUnit<Candidate> candidateGenerator =
                constructor(Candidate.class).params(
                        names().first(),
                        strings(),
                        strings()
                );
        MockUnit<Elector> electorGenerator =
                constructor(Elector.class).params(
                        names().first(),
                        passwords(),
                        1,
                        emails()
                );
        MockUnit<Ballot> ballotGenerator =
                constructor(Ballot.class).params(
                        strings(),
                        localDates(),
                        localDates(),
                        bools(),
                        bools(),
                        intSeq().start(1),
                        generatorTo3
                        );

        this.forums = forumGenerator.list(3).get();
        this.posts = postGenerator.list(12).get();
        this.electors = electorGenerator.list(10).get();
        this.candidates = candidateGenerator.list(5).get();
        this.ballots = ballotGenerator.list(3).get();

        //ballot 1 has 4 candidates
        this.ballots.get(0).addCandidate(this.candidates.get(0));
        this.ballots.get(0).addCandidate(this.candidates.get(2));
        this.ballots.get(0).addCandidate(this.candidates.get(3));
        this.ballots.get(0).addCandidate(this.candidates.get(4));
        //ballot 2 has 2 candidates
        this.ballots.get(1).addCandidate(this.candidates.get(1));
        this.ballots.get(1).addCandidate(this.candidates.get(3));
        //ballot 3 has 5 candidates
        this.ballots.get(2).addCandidate(this.candidates.get(0));
        this.ballots.get(2).addCandidate(this.candidates.get(1));
        this.ballots.get(2).addCandidate(this.candidates.get(2));
        this.ballots.get(2).addCandidate(this.candidates.get(3));
        this.ballots.get(2).addCandidate(this.candidates.get(4));

        //ballot 1 has 7 electors
        this.ballots.get(0).addElector(this.electors.get(0));
        this.ballots.get(0).addElector(this.electors.get(2));
        this.ballots.get(0).addElector(this.electors.get(4));
        this.ballots.get(0).addElector(this.electors.get(5));
        this.ballots.get(0).addElector(this.electors.get(7));
        this.ballots.get(0).addElector(this.electors.get(8));
        this.ballots.get(0).addElector(this.electors.get(9));
        //ballot 2 has 4 electors
        this.ballots.get(1).addElector(this.electors.get(1));
        this.ballots.get(1).addElector(this.electors.get(2));
        this.ballots.get(1).addElector(this.electors.get(3));
        this.ballots.get(1).addElector(this.electors.get(8));
        //ballot 3 has 10 electors
        this.ballots.get(2).addElector(this.electors.get(0));
        this.ballots.get(2).addElector(this.electors.get(1));
        this.ballots.get(2).addElector(this.electors.get(2));
        this.ballots.get(2).addElector(this.electors.get(3));
        this.ballots.get(2).addElector(this.electors.get(4));
        this.ballots.get(2).addElector(this.electors.get(5));
        this.ballots.get(2).addElector(this.electors.get(6));
        this.ballots.get(2).addElector(this.electors.get(7));
        this.ballots.get(2).addElector(this.electors.get(8));
        this.ballots.get(2).addElector(this.electors.get(9));

        this.votes = new ArrayList<>();
        for (Ballot ballot:this.ballots) {
            this.votes.addAll(voteGeneration(ballot.getId()));
        }
    }

    private List<Vote> voteGeneration(int pollId) {
        List<Vote> currentVotes = new ArrayList<>();
        Set<Integer> randomCandidateId;
        int numberOfCandidates = this.ballots.stream().filter(b -> b.getId()==pollId).findFirst().get().getCandidates().size();
        for (Elector e:this.electors) {
            randomCandidateId = new LinkedHashSet<>();
            while(randomCandidateId.size()<numberOfCandidates)
                randomCandidateId.add(ints().range(1, numberOfCandidates+1).get());
            MockNeat mock = MockNeat.threadLocal();
            List<Integer> randomCandidatesIdList = randomCandidateId.stream().toList();
            for (int i = 0; i < numberOfCandidates; i++) {
                currentVotes.add(new Vote(mock.localDates().get(),i+1,pollId,randomCandidatesIdList.get(i),e.getId()));
            }
        }
        return currentVotes;
    }


    public List<Vote> getVotes() {
        return votes;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public List<Elector> getElectors() {
        return electors;
    }

    public List<Ballot> getBallots() {
        return ballots;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public List<Forum> getForums() {
        return forums;
    }
}
