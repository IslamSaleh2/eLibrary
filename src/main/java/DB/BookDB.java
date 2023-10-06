package DB;

import entities.Book;
import entities.IssueBookEntity;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookDB {
    public static int save(Book book){
        int status=0;
        try{
            Connection con=Db.getCon();
            PreparedStatement ps=con.prepareStatement("insert into e_book(callno,name,author,publisher,quantity,issued) value(?,?,?,?,?,?)");
            ps.setString(1,book.getCallno());
            ps.setString(2, book.getName());
            ps.setString(3, book.getAuthor());
            ps.setString(4, book.getPublisher());
            ps.setInt(5, book.getQuantity());
            ps.setInt(6,0);
            status=ps.executeUpdate();
            con.close();

        }catch (Exception e){
            System.out.println(e);
        }
        return status;
    }
    public static List<Book> view(){
        List<Book> list=new ArrayList<Book>();
        try{
            Connection con=Db.getCon();
            PreparedStatement ps=con.prepareStatement("SELECT * FROM e_book");
            ResultSet rs= ps.executeQuery();
            while (rs.next()){
                Book book=new Book();
                book.setCallno(rs.getString("callno"));
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setQuantity(rs.getInt("quantity"));
                book.setIssued(rs.getInt("issued"));
                list.add(book);
            }
            con.close();
        }catch (Exception e){
            System.out.println(e);
        }

        return list;
    }
    public static int getIssued(String callno){
        int issued=0;
        try{
            Connection con=Db.getCon();
            PreparedStatement ps=con.prepareStatement("select * from e_book where callno=?");
            ps.setString(1,callno);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                issued=rs.getInt("issued");
            }
            con.close();

        }catch(Exception e){System.out.println(e);}

        return issued;
    }
    public static boolean checkIssue(String callno){
        boolean status=false;
        try{
            Connection con=Db.getCon();
            PreparedStatement ps=con.prepareStatement("select * from e_book where callno=? and quantity>issued");
            ps.setString(1,callno);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                status=true;
            }
            con.close();

        }catch(Exception e){System.out.println(e);}

        return status;
    }

    public static int issueBook(IssueBookEntity bean) {
        String callno=bean.getCallno();
        boolean checkstatus=checkIssue(callno);
        System.out.println("Check status: "+checkstatus);
        if(checkstatus){
            int status=0;
            try{
                Connection con=Db.getCon();
                PreparedStatement ps=con.prepareStatement("insert into e_issuebook values(?,?,?,?,?,?)");
                ps.setString(1,bean.getCallno());
                ps.setString(2,bean.getStudentid());
                ps.setString(3,bean.getStudentname());
                ps.setLong(4,bean.getStudentmobile());
                java.sql.Date currentDate=new java.sql.Date(System.currentTimeMillis());
                ps.setDate(5,currentDate);
                ps.setString(6,"no");

                status=ps.executeUpdate();
                if(status>0){
                    PreparedStatement ps2=con.prepareStatement("update e_book set issued=? where callno=?");
                    ps2.setInt(1,getIssued(callno)+1);
                    ps2.setString(2,callno);
                    status=ps2.executeUpdate();
                }
                con.close();

            }catch(Exception e){System.out.println(e);}

            return status;
        }else{
            return 0;
        }
    }

    public static List<IssueBookEntity> viewIssuedBooks(){
        List<IssueBookEntity> list=new ArrayList<IssueBookEntity>();
        try{
            Connection con=Db.getCon();
            PreparedStatement ps=con.prepareStatement("select * from e_issuebook order by issueddate desc");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                IssueBookEntity bean=new IssueBookEntity();
                bean.setCallno(rs.getString("callno"));
                System.out.println(rs.getString("callno"));
                bean.setStudentid(rs.getString("studentid"));
                bean.setStudentname(rs.getString("studentname"));
                System.out.println(rs.getString("studentname"));
                bean.setStudentmobile(rs.getLong("studentmobile"));
                bean.setIssueddate(rs.getDate("issueddate"));
                bean.setReturnstatus(rs.getString("returnstatus"));
                list.add(bean);
            }
            con.close();

        }catch(Exception e){System.out.println(e);}

        return list;
    }

    public static int returnBook(String callno, int studentid) {
        int status=0;
        try{
            Connection con=Db.getCon();
            PreparedStatement ps=con.prepareStatement("update e_issuebook set returnstatus='yes' where callno=? and studentid=?");
            ps.setString(1,callno);
            ps.setInt(2,studentid);

            status=ps.executeUpdate();
            if(status>0){
                PreparedStatement ps2=con.prepareStatement("update e_book set issued=? where callno=?");
                ps2.setInt(1,getIssued(callno)-1);
                ps2.setString(2,callno);
                status=ps2.executeUpdate();
            }
            con.close();

        }catch(Exception e){System.out.println(e);}

        return status;
    }
    public static int delete(String callno){
        int status=0;
        try{
            Connection con=Db.getCon();
            PreparedStatement ps=con.prepareStatement("delete from e_book where callno=?");
            ps.setString(1,callno);
            status=ps.executeUpdate();
            con.close();

        }catch(Exception e){System.out.println(e);}

        return status;
    }
}
