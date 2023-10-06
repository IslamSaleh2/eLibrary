package servlets;

import DB.LibrarianDB;
import entities.Librarian;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/AddLibrarian")
public class AddLibrarian extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out=resp.getWriter();

        out.print("<!DOCTYPE html>");
        out.print("<html>");
        out.println("<head>");
        out.println("<title>Add Librarian</title>");
        out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
        out.println("</head>");
        out.println("<body>");

        req.getRequestDispatcher("navadmin.html").include(req, resp);
        out.println("<div class='container'>");
        String name=req.getParameter("name");
        String email=req.getParameter("email");
        String pass=req.getParameter("password");
        String mob=req.getParameter("mobile");
        long mobile=Integer.parseInt(mob);
        Librarian librarian=new Librarian(name,email,pass,mobile);
        if(LibrarianDB.save(librarian)>0)
            out.print("<h4>Librarian added successfully</h4>");
        else
            out.print("<h4>Unable to Librarian</h4>");
        req.getRequestDispatcher("addlibrarianform.html").include(req, resp);

        out.println("</div>");
        req.getRequestDispatcher("footer.html").include(req, resp);
        out.close();

    }
}
