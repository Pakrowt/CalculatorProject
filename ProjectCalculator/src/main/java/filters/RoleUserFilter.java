package filters;

import dao.UsersDao;
import entity.Users;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

@WebFilter("/validate")
public class RoleUserFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init role filter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String login = ((HttpServletRequest) servletRequest).getParameter("loginOut");
        String currentRole = UsersDao.getInstance().checkRole(login);
        final String USER = "USER";
        final String ADMIN = "ADMIN";

        if (currentRole.equals(USER)) {
            servletRequest.getRequestDispatcher("/homePage.jsp").forward(servletRequest, servletResponse);
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (currentRole.equals(ADMIN)) {
//            ((HttpServletResponse)servletResponse).sendRedirect("/adminpage");
            servletRequest.getRequestDispatcher("/adminpage").forward(servletRequest, servletResponse);
            filterChain.doFilter(servletRequest, servletResponse);
        } else
            servletRequest.getRequestDispatcher("registration.jsp").forward(servletRequest, servletResponse);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("destroy role filter");
    }
}
