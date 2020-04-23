package sample;
import java.sql.*;
import java.util.HashMap;

public class StoreInDatabase {
    public StoreInDatabase(HashMap<String, Integer> hm1){
        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://localhost/wordoccurrences";

        final String USER = "root";
        final String PASS = "Test123";

        Connection conn = null;
        Statement stmt = null;

        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;

            for(int i = 0; i < hm1.size(); i++){
                sql = "INSERT INTO wordoccurrences.word (word) values (?)";
                PreparedStatement prpStmt = conn.prepareStatement(sql);
                prpStmt.setString(1, hm1.keySet().toArray()[i].toString());
                prpStmt.execute();
                //hm1.keySet().toArray()[i]
                //hm1.entrySet().toArray()[hm1.size()-i]
            }
//            String sql1;
//            sql1 = "SELECT * FROM wordoccurrences.word";
//            ResultSet rs = stmt.executeQuery(sql1);
//
//            //STEP 5: Extract data from result set
//            while(rs.next()){
//                //Retrieve by column name
//                String word = rs.getString("word");
//
//                //Display values
//                System.out.print("Word: " + word);
//            }
//            //STEP 6: Clean-up environment
//            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
    }
    public void addWordsToDatabase(){

    }

}
