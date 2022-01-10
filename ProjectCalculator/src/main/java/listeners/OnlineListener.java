package listeners;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class OnlineListener implements HttpSessionListener {

    private int count = 0;

    @Override
    public void sessionCreated(HttpSessionEvent hse) {
        count++;
        ServletContext context = hse.getSession().getServletContext();
        context.setAttribute("count", count);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent hse) {
        count--;
        ServletContext context = hse.getSession().getServletContext();
        context.setAttribute("count", count);
    }
}