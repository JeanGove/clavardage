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
                         "IdHistory      INTEGER NOT NULL" +
                         "Message VARCHAR(5000) NOT NULL";
                         
          int rs= state.executeUpdate(sql);
          return rs;
           
    }
    public int insertBD (Connection con, Message mess, int num) throws SQLException{
        String string = mess.ToString(mess);
        String query = "INSERT INTO HISTORY VALUES (num,mess.getSourceId(),mess.getDestId(),string)";
        Statement state = con.createStatement();
        int rs= state.executeUpdate(query); 
        return rs;
    }
    
    public Message selectBD (Connection con, int idhis) throws SQLException{
            ArrayList<String> list = new ArrayList<String>();
            ArrayList<Message> list1 = new ArrayList<Message>();
            String query = "SELECT Message "+ "FROM HISTORY where IdHistory = idhis";
            Statement state = con.createStatement();
            ResultSet rs = state.executeQuery(query);
            while(rs.next()){
                list.add(rs.getString("Message"));
            }
            
       
    
    }
    public void delectBD (Connection con, int idhis) throws SQLException{
         
            String query= "DELETE FROM HISTORY where IdHistory= idhis";
            Statement state = con.createStatement();
            int rs= state.executeUpdate(query);
    
    }

    
}
