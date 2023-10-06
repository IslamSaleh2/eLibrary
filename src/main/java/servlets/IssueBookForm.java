package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/IssueBookForm")
public class IssueBookForm extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out=resp.getWriter();
        System.out.println("IssueBookForm");
        out.print("<!DOCTYPE html>");
        out.print("<html>");
        out.println("<head>");
        out.println("<title>Issue Book Form</title>");
        out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
        out.println("</head>");
        out.println("<body>");
        req.getRequestDispatcher("navlibrarian.html").include(req, resp);

        out.println("<div class='container'>");
        req.getRequestDispatcher("issuebookform.html").include(req, resp);
        out.println("</div>");


        req.getRequestDispatcher("footer.html").include(req, resp);
        System.out.println("IssueBookForm");
        out.close();
    }
}