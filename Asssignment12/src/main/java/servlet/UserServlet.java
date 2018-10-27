package servlet;

import dao.UserDAO;
import entity.User;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserServlet extends HttpServlet {

//    public static void main (String []args){
//        dao.UserDAO userDAO=new dao.UserDAO(new Configuration().configure().buildSessionFactory());
//        User user=new User("gigel","parola","administrator");
//        boolean userinsert=userDAO.insertCity(user);
//        System.out.println(userinsert);
//    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n" +
                "<html>\n" +
                "   <body>\n" +
                "      <form action = \"/user\" method = \"POST\">\n" +
                "         Username: <input type = \"text\" name = \"username\">\n" +
                "         <br />\n" +
                "         Password: <input type = \"password\" name = \"password\" />\n" +
                "         <br>" +
                "         <input type = \"submit\" value = \"Login\" />\n" +
                "      </form>\n" +
                "   </body>\n" +
                "</html>";
//        dispatcher.forward(request, response);
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println(page);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        UserDAO userDAO=new UserDAO(new Configuration().configure().buildSessionFactory());
        User user=userDAO.findByUsername(username);
        System.out.println(user.toString());
        if(user.getRole().equals("administrator")){
            request.getSession().setAttribute("loggedUser",username);
            request.getSession().setAttribute("loggedUserRole","administrator");
            response.sendRedirect("flight");
        }
        else
        {
            request.getSession().setAttribute("loggedUser",username);
            request.getSession().setAttribute("loggedUserRole","client");
            response.sendRedirect("client");
        }
    }

}
