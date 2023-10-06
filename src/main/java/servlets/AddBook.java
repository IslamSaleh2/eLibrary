package servlets;

import DB.BookDB;
import entities.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/AddBook")
public class AddBook extends HttpServlet {
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
        String callno= req.getParameter("callno");
        String name=req.getParameter("name");
        String author=req.getParameter("author");
        String publisher=req.getParameter("publisher");
        String quan=req.getParameter("quantity");
        int quantity=Integer.parseInt(quan);

        Book book=new Book(callno,name,author,publisher,quantity);
        int i= BookDB.save(book);
        if(i>0){
            out.println("<h3>Book saved successfully</h3>");
        }
        else{
            out.println("<h3>Unable To Add Book</h3>");
        }
        req.getRequestDispatcher("addbookform.html").include(req, resp);
        out.println("</div>");


        req.getRequestDispatcher("footer.html").include(req, resp);
        out.close();

    }
}
