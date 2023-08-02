package application.ndiaya;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EtudiantImpl implements IEtudiant{

    private DB db = new DB();
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public boolean add(Etudiant etudiant) {
        // TODO Auto-generated method stub
        try {
            preparedStatement=this.db.toInsUpdDel("INSERT INTO etudiant (nom, prenom, adresse, idclasse) VALUE (?,?,?,?)");
            preparedStatement.setString(1, etudiant.getNom());
            preparedStatement.setString(2, etudiant.getPrenom());
            preparedStatement.setString(3, etudiant.getAdresse());
            preparedStatement.setInt(4, etudiant.getIdClasse());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean add(String nom, String prenom, String adresse, Integer idClasse) {
        // TODO Auto-generated method stub
        try {
            preparedStatement=this.db.toInsUpdDel("INSERT INTO etudiant (nom, prenom, adresse, idclasse) VALUE (?,?,?,?)");
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setString(3, adresse);
            preparedStatement.setInt(4, idClasse);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Etudiant> list() {
        List<Etudiant> etudiantList = new ArrayList<>();
        try {
            preparedStatement = this.db.toSelect("SELECT * FROM etudiant");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Etudiant etudiant = new Etudiant();
                etudiant.setId(resultSet.getInt("id"));
                etudiant.setNom(resultSet.getString("nom"));
                etudiant.setPrenom(resultSet.getString("prenom"));
                etudiant.setAdresse(resultSet.getString("adresse"));
                etudiant.setIdClasse(resultSet.getInt("idClasse"));

                etudiantList.add(etudiant);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return etudiantList;
    }

    @Override
    public List<ChartPattern> getNbEtudiantsParClasse() {
        List<ChartPattern> listEtudiants = new ArrayList<>();
        try {
            preparedStatement = this.db.toSelect("SELECT c.libelle AS classe_libelle, COUNT(e.id) AS nombre_etudiants\n" +
                    "FROM Classe c\n" +
                    "LEFT JOIN Etudiant e ON c.id = e.idClasse\n" +
                    "GROUP BY c.libelle;\n");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                listEtudiants.add(new ChartPattern(resultSet.getString("classe_libelle"),
                            Double.parseDouble(String.valueOf(resultSet.getInt("nombre_etudiants")))
                        ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listEtudiants;
    }
}
