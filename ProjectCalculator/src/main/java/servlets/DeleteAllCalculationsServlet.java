package servlets;

import dao.CalculationDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteAll")
public class DeleteAllCalculationsServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(DeleteAllCalculationsServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        CalculationDao.getInstance().deleteAllUserCalculations(userId);
        log.info("delete all user calculations " + userId);

        req.getRequestDispatcher("homePage.jsp").forward(req, resp);
    }
}
