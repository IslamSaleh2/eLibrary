package DB;


import entities.Librarian;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LibrarianDB {
    public static boolean authentication(String email, String pass){
        boolean status =false;
        try{
            Connection con=Db.getCon();
            PreparedStatement ps=con.prepareStatement("select * from e_librarian where email=? and password=?");
            ps.setString(1,email);
            ps.setString(2,pass);
            ResultSet rs= ps.executeQuery();

            if(rs.next()){
                status=true;
            }
            con.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return status;
    }
    public static int save(Librarian librarian){
        int status=0;
        try{
         Connection con=Db.getCon();
         PreparedStatement ps=con.prepareStatement("insert into e_librarian(name,email,password,mobile) value(?,?,?,?)");
         ps.setString(1,librarian.getName());
         ps.setString(2,librarian.getEmail());
         ps.setString(3,librarian.getPassword());
         ps.setLong(4,librarian.getMobile());
         status=ps.executeUpdate();
         con.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return status;
    }
    public static List<Librarian> view(){
        List<Librarian> list=new ArrayList<Librarian>();
        try{
            Connection con=Db.getCon();
            PreparedStatement ps=con.prepareStatement("select * from e_librarian");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Librarian librarian=new Librarian();
                librarian.setId(rs.getInt("id"));
                librarian.setName(rs.getString("name"));
                librarian.setEmail(rs.getString("email"));
                librarian.setPassword(rs.getString("password"));
                librarian.setMobile(rs.getLong("mobile"));
                list.add(librarian);
            }
            con.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return list;
    }
    public static int delete(int id){
        int status=0;
        try{
            Connection con=Db.getCon();
            PreparedStatement ps=con.prepareStatement("delete from e_librarian where id=?");
            ps.setInt(1,id);
            status=ps.executeUpdate();
            con.close();

        }catch(Exception e){System.out.println(e);}

        return status;
    }
    public static Librarian viewById(int id){
        Librarian bean=new Librarian();
        try{
            Connection con=Db.getCon();
            PreparedStatement ps=con.prepareStatement("select * from e_librarian where id=?");
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                bean.setId(rs.getInt(1));
                bean.setName(rs.getString(2));
                bean.setPassword(rs.getString(3));
                bean.setEmail(rs.getString(4));
                bean.setMobile(rs.getLong(5));
            }
            con.close();

        }catch(Exception e){System.out.println(e);}

        return bean;
    }
    public static int update(Librarian bean){
        int status=0;
        try{
            Connection con=Db.getCon();
            PreparedStatement ps=con.prepareStatement("update e_librarian set name=?,email=?,password=?,mobile=? where id=?");
            ps.setString(1,bean.getName());
            ps.setString(2,bean.getEmail());
            ps.setString(3,bean.getPassword());
            ps.setLong(4,bean.getMobile());
            ps.setInt(5,bean.getId());
            status=ps.executeUpdate();
            con.close();

        }catch(Exception e){System.out.println(e);}

        return status;
    }
}
