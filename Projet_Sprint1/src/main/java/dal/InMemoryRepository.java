package dal;

import bll.model.*;
import net.andreinc.mockneat.abstraction.MockUnit;
import net.andreinc.mockneat.abstraction.MockUnitInt;

import java.util.List;

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
        MockUnitInt generatorTo5 = ints().range(1, 6);
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
        MockUnit<Vote> voteGenerator =
                constructor(Vote.class).params(
                        localDates(),
                        intSeq().start(1).max(3),
                        intSeq().start(1).max(3),
                        generatorTo5,
                        intSeq().start(1).max(10)
                );

        this.forums = forumGenerator.list(3).get();
        this.posts = postGenerator.list(12).get();
        this.electors = electorGenerator.list(10).get();
        this.candidates = candidateGenerator.list(5).get();
        this.ballots = ballotGenerator.list(3).get();
        this.votes = voteGeneration();

        for (Elector elector:this.electors) {
            for (Ballot ballot:this.ballots) {
                ballot.addElector(elector);
            }
        }
        for (Candidate candidate:this.candidates) {
            for (Ballot ballot:this.ballots) {
                ballot.addCandidate(candidate);
            }
        }
    }

    private List<Vote> voteGeneration() {
        List
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
