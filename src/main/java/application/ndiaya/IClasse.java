package application.ndiaya;

import java.util.List;

public interface IClasse {
    public boolean add(Classe classe);
    public boolean add(String libelle);

    public List<Classe> list();

    public boolean edit(Classe classe);
    public boolean edit(int id, String libelle);

    public boolean delete(int id);
}
