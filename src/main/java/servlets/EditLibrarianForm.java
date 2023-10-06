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

@WebServlet("/EditLibrarianForm")
public class EditLibrarianForm extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out=resp.getWriter();

        out.print("<!DOCTYPE html>");
        out.print("<html>");
        out.println("<head>");
        out.println("<title>Edit Librarian Form</title>");
        out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
        out.println("</head>");
        out.println("<body>");

        req.getRequestDispatcher("navadmin.html").include(req, resp);
        out.println("<div class='container'>");
        String sid=req.getParameter("id");
        int id=Integer.parseInt(sid);

        Librarian bean= LibrarianDB.viewById(id);

        out.print("<form action='EditLibrarian' method='post' style='width:300px'>");
        out.print("<div class='form-group'>");
        out.print("<input type='hidden' name='id' value='"+bean.getId()+"'/>");
        out.print("<label for='name1'>Name</label>");
        out.print("<input type='text' class='form-control' value='"+bean.getName()+"' name='name' id='name1' placeholder='Name'/>");
        out.print("</div>");
        out.print("<div class='form-group'>");
        out.print("<label for='email1'>Email address</label>");
        out.print("<input type='email' class='form-control' value='"+bean.getEmail()+"'  name='email' id='email1' placeholder='Email'/>");
        out.print("</div>");
        out.print("<div class='form-group'>");
        out.print("<label for='password1'>Password</label>");
        out.print("<input type='password' class='form-control' value='"+bean.getPassword()+"'  name='password' id='password1' placeholder='Password'/>");
        out.print("</div>  ");
        out.print("<div class='form-group'>");
        out.print("<label for='mobile1'>Mobile Number</label>");
        out.print("<input type='number' class='form-control' value='"+bean.getMobile()+"'  name='mobile' id='mobile1' placeholder='Mobile'/>");
        out.print("</div>");
        out.print("<button type='submit' class='btn btn-primary'>Update</button>");
        out.print("</form>");

        out.println("</div>");
        req.getRequestDispatcher("footer.html").include(req, resp);
        out.close();
    }
}
