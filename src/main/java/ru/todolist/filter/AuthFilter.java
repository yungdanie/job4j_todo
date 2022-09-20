package ru.todolist.filter;

import org.springframework.stereotype.Component;
import ru.todolist.model.User;
import ru.todolist.util.AuthUserUtil;
import ru.todolist.util.SessionUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        String uri = req.getRequestURI();
        if (AuthUserUtil.isAuth(session) && uri.endsWith("regUser") || uri.endsWith("authUser")) {
            res.sendRedirect("index");
            return;
        }
        chain.doFilter(req, res);
    }

}
