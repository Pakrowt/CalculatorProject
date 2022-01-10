package servlets;

import dao.UsersDao;
import entity.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/reg")
public class RegistrationValidServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(RegistrationValidServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        log.info("Get all parameters (login,password,name ");
        ServletContext servletContext = getServletContext();
        servletContext.setAttribute("name", name);

        UsersDao newUser = UsersDao.getInstance();
        newUser.insert(new Users(login, password, "USER"));
        log.info("Create new user in DB" + newUser);

        servletContext.getRequestDispatcher("/login.jsp").forward(req, resp);
    }
}
