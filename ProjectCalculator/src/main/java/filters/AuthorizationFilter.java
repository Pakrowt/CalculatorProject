package filters;

import dao.UsersDao;
import entity.Users;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebFilter("/validate")
public class AuthorizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String login = servletRequest.getParameter("loginOut");
        String password = servletRequest.getParameter("passwordOut");
        String name = servletRequest.getParameter("name");
        UsersDao instance = UsersDao.getInstance();
        Optional<Users> users = instance.checkLoginAndPassword(login, password);
        if (users.isPresent()) {
            HttpSession session = ((HttpServletRequest) servletRequest).getSession();
            session.setAttribute("user_id", login);
            filterChain.doFilter(servletRequest, servletResponse);
        } else
            servletRequest.getRequestDispatcher("/wrongPassword.html").forward(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
