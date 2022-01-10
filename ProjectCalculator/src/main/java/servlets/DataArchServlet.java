package servlets;

import dao.CalculationDao;
import entity.Calculation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@WebServlet("/data")
public class DataArchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = (String) req.getSession().getAttribute("user_id");
        List<Calculation> calculations = CalculationDao.getInstance().select(login);
        getServletContext().setAttribute("calculations", calculations);

        req.getRequestDispatcher("adminhomepage.jsp").forward(req, resp);
    }
}
