package application.ndiaya;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClasseImpl implements IClasse{

    private DB db = new DB();
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public boolean add(Classe classe) {
        // TODO Auto-generated method stub
        try {
            preparedStatement=this.db.toInsUpdDel("INSERT INTO classe (libelle) VALUE (?)");
            preparedStatement.setString(1, classe.getLibelle());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean add(String libelle) {
        // TODO Auto-generated method stub
        try {
            preparedStatement=this.db.toInsUpdDel("INSERT INTO classe (libelle) VALUES (?)");
            preparedStatement.setString(1, libelle);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Classe> list() {
        List<Classe> classeList = new ArrayList<>();
        try {
            preparedStatement = this.db.toSelect("SELECT * FROM classe");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Classe classe = new Classe();
                classe.setId(resultSet.getInt("id"));
                classe.setLibelle(resultSet.getString("libelle"));
                classeList.add(classe);

//                classeList.add(new Classe(
//                    resultSet.getInt("id"),
//                    resultSet.getString("libelle")
//                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classeList;

    }
}