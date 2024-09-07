package vn.edu.iuh.fit.tranthithanhtuyen_session01;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.tranthithanhtuyen_session01.POJO.User;
import vn.edu.iuh.fit.tranthithanhtuyen_session01.beans.LoginBean;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("pwd");
        User user = new User(username, password);
        LoginBean loginBean = new LoginBean();

        PrintWriter out = resp.getWriter();

        boolean isLogin = loginBean.login(user);
        if (isLogin)
            out.println("Login success");
        else
            out.println("Login failed");
    }
}
