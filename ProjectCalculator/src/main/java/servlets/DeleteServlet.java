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
import java.util.Arrays;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(DeleteServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer resultId = Integer.parseInt(req.getParameter("resultId"));

        CalculationDao.getInstance().delete(resultId);
        log.info("delete result № " + resultId);

        req.getRequestDispatcher("homePage.jsp").forward(req, resp);

    }
}
