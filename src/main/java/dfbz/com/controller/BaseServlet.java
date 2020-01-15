package dfbz.com.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String uri = req.getRequestURI();
        String[] split = uri.split("/");
        String methodName = split[3];
        System.out.println(methodName);
        String className = this.getClass().getName();
        System.out.println(className);
        try {
            Class<?> aClass = Class.forName(className);
            Method method = aClass.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, req, resp);
        } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            if (req.getSession().getAttribute("user") != null)
                resp.sendRedirect("/html/home.html");
            else
                resp.sendRedirect("/index.jsp");
        }
    }
}
