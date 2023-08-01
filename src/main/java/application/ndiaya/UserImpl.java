package application.ndiaya;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserImpl implements IUser{

    private DB db = new DB();
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public boolean add(User user) {
        // TODO Auto-generated method stub
        try {
            preparedStatement=this.db.toInsUpdDel("INSERT INTO user (nom,prenom,telephone,email,login,password) VALUES (?,?,?,?,?,?)");
            preparedStatement.setString(1, user.getNom());
            preparedStatement.setString(2, user.getPrenom());
            preparedStatement.setString(3, user.getTelephone());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getLogin());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean add(String nom, String prenom, String telephone, String email, String login, String password) {
        // TODO Auto-generated method stub
        try {
            preparedStatement=this.db.toInsUpdDel("INSERT INTO user (nom,prenom,telephone,email,login,password) VALUES (?,?,?,?,?,?)");
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setString(3, telephone);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, login);
            preparedStatement.setString(6, password);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean login(User user) {
        try {
            preparedStatement=this.db.toSelect("SELECT * FROM user WHERE login=? AND password=?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            resultSet=preparedStatement.executeQuery();
            if(resultSet.first())return true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean login(String login, String password) {
        // TODO Auto-generated method stub
        try {
            preparedStatement=this.db.toSelect("SELECT * FROM user WHERE login=? AND password=?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.first())return true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
}
