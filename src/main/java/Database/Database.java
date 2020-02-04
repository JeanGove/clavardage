/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

/**
 *
 * @author corentin
 */
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

public class Database {
    
    public Connection Database() throws ClassNotFoundException, SQLException{  
        Connection con = null;
        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection("jdbc:sqlite:MovieCatalog");
        return con;
    }
    
   
    public int CreateBD (Connection con) throws SQLException{
        Statement state = con.createStatement();
        // Créer la table
        String sql = "CREATE TABLE HISTORY NOT NULL" +
                     "IdSource      INTEGER NOT NULL" +
                     "IdDestination     INTEGER NOT NULL" +
                     "Content VARCHAR(5000) NOT NULL" +
                     "Date DATE     NOT NULL";

        int rs= state.executeUpdate(sql);
        state.close();
        return rs;
           
    }
    public int insertBD (Connection con, Message mess) throws SQLException{

        String query = "INSERT INTO HISTORY VALUES (mess.getSourceId(),mess.getDestId(),getContent(),getDate())";
        Statement state = con.createStatement();
        int rs= state.executeUpdate(query); 
        state.close();
        return rs;

    }
    
    public  ArrayList<Message> selectBD (Connection con, int sourceId, int destId) throws SQLException{
            String content = null;
            Date date = null;
            ArrayList<Message> list = new ArrayList<Message>();
            String query = "SELECT * "+ "FROM HISTORY where IdSource = sourceId AND IdDestination = destId";
            Statement state = con.createStatement();
            ResultSet rs = state.executeQuery(query);
            while(rs.next()){
                content= rs.getString("Content");
                date = rs.getDate("Date");
                Message mess= new Message(date, sourceId, destId, content,"text");
                list.add(mess);
            }
            rs.close();
            state.close();
           return list;
    
    }
    public void delectBD (Connection con,int sourceId, int destId, Date date) throws SQLException{
         
            String query= "DELETE FROM HISTORY where IdDestination = destId AND Date = date";
            Statement state = con.createStatement();
            int rs= state.executeUpdate(query);
            state.close();
    
    }

    
}