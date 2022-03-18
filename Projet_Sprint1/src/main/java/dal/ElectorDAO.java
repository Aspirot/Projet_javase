package dal;

import bll.model.Elector;

import java.util.ArrayList;
import java.util.List;

public class ElectorDAO implements IElectorDAO{
    List<Elector> electors;

    ElectorDAO(InMemoryRepository memoryRepository){
        electors = new ArrayList<>();
        memoryRepository.getElectors().stream().forEach(e -> addElector(e));
    }
    @Override
    public void addElector(Elector elector) {
        this.electors.add(elector);
    }

    @Override
    public List<Elector> getAllElectors() {
        return this.electors;
    }

    @Override
    public void deleteElector(int electorId) {
        this.electors.remove(this.electors.stream().filter(e -> e.getId()==electorId).findFirst());
    }
}
