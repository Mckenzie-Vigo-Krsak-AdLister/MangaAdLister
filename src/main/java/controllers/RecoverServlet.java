package controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Recover", urlPatterns = "/recover")
public class RecoverServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        try{
            req.getRequestDispatcher("/WEB-INF/recover.jsp").forward(req,res);
        }catch(Exception e){
            throw e;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        try{

        }catch(Exception e){
            throw e;
        }
    }
}
