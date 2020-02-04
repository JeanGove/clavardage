/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author corentin
 */
public class DatabaseTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            InetAddress addr = InetAddress.getLocalHost();
            Database db = new Database("ClavardApp",addr);
        } catch (UnknownHostException ex) {
            Logger.getLogger(DatabaseTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}

class Database{
    public Connection connection;
    
    public Database(String database,InetAddress addr) {
       /* try {
            Connection con = null;
            Class cls = Class.forName("com.mysql.jdbc.Driver");
            
            try{
                con =
                   DriverManager.getConnection("jdbc:mysql://localhost:3306/ClavardApp?" +
                                               "user=serviceBDD&password=bT8Gg89i");

                // Do something with the Connection
                String query = "SELECT * FROM Messages";
                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery(query);
               
                System.out.println(rs.getString("content"));
                
                stm.close();
                rs.close();
            } catch (SQLException ex) {
                // handle any errors
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            
            }
            
            con.close();
            /*String URL = "localhost/ClavardApp?"
            con = DriverManager.getConnection("jdbc:mysql:"addr.getHostName()+"/"+database);
            this.connection = con;*
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }*/
       /* Connexion à la base de données */
        String url = "jdbc:mysql://localhost:3306/ClavardApp";
        String utilisateur = "serviceBDD";
        String motDePasse = "bT8Gg89i";
        Connection connexion = null;
        try {
            connexion = DriverManager.getConnection( url, utilisateur, motDePasse );

            /* Ici, nous placerons nos requêtes vers la BDD */
            /* ... */
            String query = "SELECT * FROM Messages";
            Statement stm = connexion.createStatement();
            ResultSet rs = stm.executeQuery(query);

            System.out.println(rs.getString("content"));

            stm.close();
            rs.close();
        } catch ( SQLException e ) {
            /* Gérer les éventuelles erreurs ici */
              Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if ( connexion != null )
                try {
                    /* Fermeture de la connexion */
                    connexion.close();
                } catch ( SQLException ignore ) {
                    /* Si une erreur survient lors de la fermeture, il suffit de l'ignorer. */
                }
        }
    }
}