package DB;

import java.sql.DriverManager;

public class Db {
    public static java.sql.Connection getCon(){
        java.sql.Connection con=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","root");
        }catch(Exception e){System.out.println(e);}
        return con;
    }
}
