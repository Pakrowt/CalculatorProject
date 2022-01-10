package servlets;

import dao.CalculationDao;
import entity.Calculation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/adminpage")
public class AdminPageServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(CalculateAreaRectangleServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CalculationDao calculationDao = CalculationDao.getInstance();
        List<Calculation> calculations = calculationDao.selectAll();
        req.setAttribute("calculations", calculations);
        log.info("get all calculations");

        req.getRequestDispatcher("adminhomepage.jsp").forward(req, resp);
    }
}
