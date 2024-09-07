package vn.edu.iuh.fit.thanhtuyen.labweek1.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.thanhtuyen.labweek1.entities.Account;
import vn.edu.iuh.fit.thanhtuyen.labweek1.services.AccountService;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("account");
        String password = req.getParameter("pwd");
        PrintWriter out = resp.getWriter();
        AccountService accountService = new AccountService();
//        Account account = accountService.checkLogin(username, password);
        if (accountService.checkLogin(username, password)) {
            ;
        } else {
            out.println("Login failed");
        }
    }
}
