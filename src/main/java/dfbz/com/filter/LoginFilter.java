package dfbz.com.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/index.jsp")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        Cookie[] cookies = req.getCookies();
        if (cookies != null && cookies.length > 0) {
            boolean b = false;
            for (Cookie cookie : cookies) {
                System.out.println(0);
                if (cookie.getName().equals("username")) {
                    b = true;
                    break;
                }
            }
            if (b) {
                System.out.println(1);
                resp.sendRedirect(req.getContextPath() + "/user/home");
            } else {
                System.out.println(2);
                chain.doFilter(req, resp);
            }
        }

    }

    @Override
    public void destroy() {

    }
}
