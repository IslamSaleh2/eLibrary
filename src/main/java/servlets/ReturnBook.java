package servlets;

import DB.BookDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ReturnBook")
public class ReturnBook extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out=resp.getWriter();

        out.print("<!DOCTYPE html>");
        out.print("<html>");
        out.println("<head>");
        out.println("<title>Return Book</title>");
        out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
        out.println("</head>");
        out.println("<body>");
        req.getRequestDispatcher("navlibrarian.html").include(req, resp);

        out.println("<div class='container'>");
        String callno=req.getParameter("callno");
        String sstudentid=req.getParameter("studentid");
        int studentid=Integer.parseInt(sstudentid);

        int i= BookDB.returnBook(callno,studentid);
        if(i>0){
            out.println("<h3>Book returned successfully</h3>");
        }else{
            out.println("<h3>Sorry, unable to return book.</h3><p>We may have sortage of books. Kindly visit later.</p>");
        }
        out.println("</div>");


        req.getRequestDispatcher("footer.html").include(req, resp);
        out.close();
    }
}
