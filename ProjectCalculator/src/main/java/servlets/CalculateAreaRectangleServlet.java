package servlets;

import by.samarcev.RectangleCalculator;
import dao.CalculationDao;
import dao.UsersDao;
import entity.Calculation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/area")
public class CalculateAreaRectangleServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(CalculateAreaRectangleServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        try {
            String userId = (String) req.getSession().getAttribute("user_id");
            double firstSide = Double.parseDouble(req.getParameter("firstSide"));
            double secondSide = Double.parseDouble(req.getParameter("secondSide"));

            RectangleCalculator rectangleAreaCalculator = new RectangleCalculator();
            double areaRectangle = rectangleAreaCalculator.doubleRectangleArea(firstSide, secondSide);

            CalculationDao calculationDao = CalculationDao.getInstance();
            Calculation calculations = calculationDao.insert(new Calculation(userId, firstSide, secondSide, areaRectangle));

            ServletContext servletContext = getServletContext();
            servletContext.setAttribute("firstSide", firstSide);
            servletContext.setAttribute("secondSide", secondSide);
            servletContext.setAttribute("result", areaRectangle);
            servletContext.setAttribute("userId", userId);
            req.setAttribute("calculations", CalculationDao.getInstance().select(userId));
            req.getRequestDispatcher("homePage.jsp").forward(req, resp);

        } catch (ArithmeticException e) {
            writer.println("You typed the negative numbers! Try again, but use only positive numbers. ");
            log.error("Exception occurred ", e);

        } catch (Exception e) {
            writer.println("You typed the incorrect value! Try again, but use only numbers. ");
            log.error("Exception occurred ", e);

        } finally {
            writer.close();
        }
    }
}
