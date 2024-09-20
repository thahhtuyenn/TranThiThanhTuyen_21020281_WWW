package vn.edu.iuh.fit.thanhtuyen.labweek1.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.iuh.fit.thanhtuyen.labweek1.entities.Account;
import vn.edu.iuh.fit.thanhtuyen.labweek1.entities.GrantAccess;
import vn.edu.iuh.fit.thanhtuyen.labweek1.entities.Role;
import vn.edu.iuh.fit.thanhtuyen.labweek1.services.AccountService;
import vn.edu.iuh.fit.thanhtuyen.labweek1.services.RoleService;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


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
        HttpSession session = req.getSession();
        session.setAttribute("accountEdit", session.getAttribute("account"));
        List<Role> roles = roleService.findAll();
        req.setAttribute("roles", roles);
        if (action.equalsIgnoreCase("edit")) {
            String accountId = req.getParameter("accountId");
            account = accountService.findByAccountId(accountId);
            session.setAttribute("accountEdit", account);
            req.setAttribute("accountEdit", account);
            req.getRequestDispatcher("views/editUser.jsp").forward(req, resp);
        } else if (action.equalsIgnoreCase("delete")) {
            String accountId = req.getParameter("accountId");
            boolean status = accountService.delete(accountId);
            if (status) {
                List<Account> accounts = accountService.findAccountNotIsAdmin();
                req.setAttribute("accounts", accounts);
                Account accountLogin = (Account) session.getAttribute("account");
                req.setAttribute("accounts", accounts);
                req.setAttribute("roles", roles);
                req.getRequestDispatcher("views/dashboard.jsp").forward(req, resp);
            }
        } else if (action.equalsIgnoreCase("add")) {
            account = null;
            session.removeAttribute("accountEdit");
            req.setAttribute("accountEdit", account);
            req.getRequestDispatcher("views/editUser.jsp").forward(req, resp);
        }

        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String fullName = req.getParameter("fullName");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String accountId = req.getParameter("accountId");
        HttpSession session = req.getSession();
        //Danh sach role duoc chon
        String[] roles = req.getParameterValues("roles");
        System.out.println("roles: " + Arrays.toString(roles));
        //chuyen ve set
        Set<String> roleSet = new HashSet<>(Arrays.asList(roles));
        Set<GrantAccess> grantAccesses = new LinkedHashSet<>();

        grantAccesses = roleSet.stream().map(roleId -> {
            GrantAccess grantAccess = new GrantAccess();
            Role role = new Role();
            role.setRoleId(roleId);
            Account account = new Account();
            account.setAccountId(null);
            grantAccess.setAccount(account);
            grantAccess.setRole(role);
            return grantAccess;
        }).collect(Collectors.toSet());

        Account account = new Account(accountId, fullName, "123", email, phone, (byte) 1, grantAccesses);
        accountService.save(account);

        List<Account> accounts = accountService.findAccountNotIsAdmin();
        req.setAttribute("accounts", accounts);
        Account accountLogin = (Account) session.getAttribute("account");
        req.setAttribute("accounts", accounts);
        req.setAttribute("roles", roles);
        req.getRequestDispatcher("views/dashboard.jsp").forward(req, resp);

        super.doPost(req, resp);
    }
}
