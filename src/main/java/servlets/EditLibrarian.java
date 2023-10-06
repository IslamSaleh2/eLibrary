package servlets;

import DB.LibrarianDB;
import entities.Librarian;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/EditLibrarian")
public class EditLibrarian extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sid=req.getParameter("id");
        int id=Integer.parseInt(sid);
        String name=req.getParameter("name");
        String email=req.getParameter("email");
        String password=req.getParameter("password");
        String smobile=req.getParameter("mobile");
        long mobile=Long.parseLong(smobile);
        Librarian bean=new Librarian(id,name, email, password, mobile);
        LibrarianDB.update(bean);
        resp.sendRedirect("ViewLibrarian");
    }
}
