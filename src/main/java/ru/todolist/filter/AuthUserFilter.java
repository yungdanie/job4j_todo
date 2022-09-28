package ru.todolist.filter;

import org.springframework.stereotype.Component;
import ru.todolist.util.UserUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

@Component
public class AuthUserFilter implements Filter {

    private final Set<String> authURL;
    public AuthUserFilter() {
        authURL = Set.of("authUser", "regUser");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        String uri = req.getRequestURI();
        boolean isAuth = UserUtil.isAuth(session);
        if (isAuth) {
            if (isURLValid(uri)) {
                res.sendRedirect(req.getContextPath() + "index");
                return;
            }
        } else {
            if (!isURLValid(uri)) {
                res.sendRedirect(req.getContextPath() + "authUser");
                return;
            }
        }
        chain.doFilter(req, res);
    }

    public boolean isURLValid(String uri) {
        return authURL
                .stream()
                .anyMatch(uri::endsWith);
    }

}
