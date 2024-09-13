package vn.edu.iuh.fit.thanhtuyen.labweek1.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.iuh.fit.thanhtuyen.labweek1.entities.Account;
import vn.edu.iuh.fit.thanhtuyen.labweek1.entities.Role;
import vn.edu.iuh.fit.thanhtuyen.labweek1.services.AccountService;
import vn.edu.iuh.fit.thanhtuyen.labweek1.services.RoleService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equalsIgnoreCase("login")){
            String username = req.getParameter("account");
            String password = req.getParameter("pwd");
            PrintWriter out = resp.getWriter();
            AccountService accountService = new AccountService();
            RoleService roleService = new RoleService();
            Account accountLogin = accountService.findByAccountIdAndPassword(username, password);
            if (accountLogin != null){
                HttpSession session = req.getSession();
                session.setAttribute("account", accountLogin);
                req.setAttribute("account", accountLogin);
                List<Account> accounts = accountService.findAccountNotIsAdmin();
                List<Role> roles = roleService.findAll();
                req.setAttribute("accounts", accounts);
                req.setAttribute("roles", roles);
                if(accountLogin.getGrantAccesses().stream().anyMatch(grantAccess -> grantAccess.getRole().getRoleId().equalsIgnoreCase("admin"))) {
                    req.getRequestDispatcher("views/dashboard.jsp").forward(req, resp);
                } else {
                    req.getRequestDispatcher("views/user.jsp").forward(req, resp);
                }
            } else {
                out.println("Login fail");
            }
        } else if (action.equalsIgnoreCase("logout")) {
            HttpSession session = req.getSession();
            Account accountLogout = (Account) session.getAttribute("account");
            if(accountLogout == null){
                req.setAttribute("error", "You have not logged in yet");
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            }else {
                session.removeAttribute("account");
                req.setAttribute("message", "Logout successfully");
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            }
        }

    }
}
