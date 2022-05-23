package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import Exception.ConnectionBDException;

import javax.swing.*;

public class SingletonConnexion {
    private static Connection connexionUnique;

    public static Connection getInstance() throws ConnectionBDException {
        if(connexionUnique == null) {
            try {
                connexionUnique = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "LuAr300468");
            }
            catch(SQLException exception) {
                JOptionPane.showMessageDialog(null, "here");
            }
        }
        return connexionUnique;
    }

    public static void disconnect() throws Exception {
        if(connexionUnique != null){
            try{
                connexionUnique.close();
                connexionUnique = null;

            }
            catch (SQLException exception){
                throw new Exception();
            }
        }
    }
}
