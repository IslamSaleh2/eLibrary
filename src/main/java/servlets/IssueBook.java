package servlets;

import DB.BookDB;
import entities.IssueBookEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/IssueBook")
public class IssueBook extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out=resp.getWriter();
        out.print("<!DOCTYPE html>");
        out.print("<html>");
        out.println("<head>");
        out.println("<title>Add Book Form</title>");
        out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
        out.println("</head>");
        out.println("<body>");
        req.getRequestDispatcher("navlibrarian.html").include(req, resp);

        out.println("<div class='container'>");
        String callno=req.getParameter("callno");
        String studentid=req.getParameter("studentid");
        String studentname=req.getParameter("studentname");
        String sstudentmobile=req.getParameter("studentmobile");
        System.out.println("IssueBook");
        long studentmobile=Long.parseLong(sstudentmobile);
        IssueBookEntity bean=new IssueBookEntity(callno,studentid,studentname,studentmobile);
        int i= BookDB.issueBook(bean);
        if(i>0){
            out.println("<h3>Book issued successfully</h3>");
        }else{
            out.println("<h3>Sorry, unable to issue book.</h3><p>We may have shortage of books. Kindly visit later.</p>");
        }
        out.println("</div>");


        req.getRequestDispatcher("footer.html").include(req, resp);
        out.close();
    }
}
