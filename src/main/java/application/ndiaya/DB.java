package application.ndiaya;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DB {

    Connection connection;
    String URL = "jdbc:mysql://localhost/ndiaya";
    String user = "root";
    String password = "";
    private String driver = "com.mysql.cj.jdbc.Driver";

    public DB(){

    }

    public Connection getConnection() throws SQLException {
        if(connection==null) {
            try {
                Class.forName(driver);
                connection = DriverManager.getConnection(URL, user, password);
                System.out.println("Connexion reussie");
            } catch (SQLException | ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println("Connexion echoue");
            }
        }
        return this.connection;
    }

    public PreparedStatement toInsUpdDel(String sql) throws SQLException {
        return this.getConnection().prepareStatement(sql);
    }
    public PreparedStatement toSelect(String sql,int type1,int type2) throws SQLException {
        return this.getConnection().prepareStatement(sql,type1,type2);
    }
}
