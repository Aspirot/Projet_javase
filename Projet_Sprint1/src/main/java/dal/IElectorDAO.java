package dal;

import bll.model.Elector;

import java.util.List;

public interface IElectorDAO {
    void addElector(Elector elector);
    List<Elector> getAllElectors();
    //nous n'avons pas trouvez de update qui serait nécessaire pour les électeurs
    void deleteElector(int electorId);
}
