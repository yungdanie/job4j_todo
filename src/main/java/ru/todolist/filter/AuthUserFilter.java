package ru.todolist.filter;

import org.springframework.stereotype.Component;
import ru.todolist.util.UserUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class AuthUserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        String uri = req.getRequestURI();
        boolean isAuth = UserUtil.isAuth(session);
        if (isAuth) {
            if (uri.endsWith("authUser") || uri.endsWith("regUser")) {
                res.sendRedirect("index");
                return;
            }
        } else {
            if (!uri.endsWith("authUser") && !uri.endsWith("regUser")) {
                res.sendRedirect("authUser");
                return;
            }
        }
        chain.doFilter(req, res);
    }

}
