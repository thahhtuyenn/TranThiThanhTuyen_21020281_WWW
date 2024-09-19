package vn.edu.iuh.fit.thanhtuyen.labweek1.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.thanhtuyen.labweek1.entities.Account;
import vn.edu.iuh.fit.thanhtuyen.labweek1.entities.Role;
import vn.edu.iuh.fit.thanhtuyen.labweek1.services.AccountService;
import vn.edu.iuh.fit.thanhtuyen.labweek1.services.RoleService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "userServlet", urlPatterns = "/users")
public class UserServlet extends HttpServlet {
    @Inject
    private AccountService accountService;
    @Inject
    private RoleService roleService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        Account account = new Account();
        List<Role> roles = roleService.findAll();
        if (action.equalsIgnoreCase("edit")) {
            String accountId = req.getParameter("accountId");
            account = accountService.findByAccountId(accountId);
        }

        req.setAttribute("roles", roles);
        req.setAttribute("account", account);
        req.getRequestDispatcher("views/editUser.jsp").forward(req, resp);

        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("views/editUser.jsp").forward(req, resp);
        super.doPost(req, resp);
    }
}
