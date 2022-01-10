package servlets;

//import by.samarcev.RectangleCalculator;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//@WebServlet("/perimeter")
//public class CalculatePerimeterRectangleServlet extends HttpServlet {
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        PrintWriter writer = resp.getWriter();
//        resp.setContentType("text/html");
//        String contentType = "<!DOCTYPE html>\n";
//        writer.println(contentType + "<html>\n" + "<body>");
//        try {
//            double firstSide = Double.parseDouble(req.getParameter("firstSide"));
//            double secondSide = Double.parseDouble(req.getParameter("secondSide"));
//            RectangleCalculator rectangleCalculator = new RectangleCalculator();
//            double rectanglePerimeter = rectangleCalculator.doubleRectanglePerimeter(firstSide, secondSide);
//            writer.println("<h2>Площадь прямоугольника равна : " + rectanglePerimeter + "</h2> </body>");
//        } catch (ArithmeticException e) {
//            req.getRequestDispatcher("error.jsp").forward(req, resp);
//        } catch (Exception e) {
//            writer.println("<h2> Вы ввели неправильное значение! Попробуйте снова, но используйте только цифры. <h2></body>");
//        } finally {
//            writer.close();
//        }
//    }
//}
