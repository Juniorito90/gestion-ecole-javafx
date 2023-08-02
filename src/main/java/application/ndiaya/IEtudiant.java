package application.ndiaya;

import java.util.List;
import application.ndiaya.ChartPattern;

public interface IEtudiant {
    public boolean add(Etudiant etudiant);

    public boolean add(String nom,String prenom,String adresse,Integer idClasse);

    public List<Etudiant> list();

    public List<ChartPattern> getNbEtudiantsParClasse();
}
