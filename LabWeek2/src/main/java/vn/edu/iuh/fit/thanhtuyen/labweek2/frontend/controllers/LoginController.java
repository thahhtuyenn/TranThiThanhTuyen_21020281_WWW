package vn.edu.iuh.fit.thanhtuyen.labweek2.frontend.controllers;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.dtos.EmployeeDto;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.dtos.ProductDto;
import vn.edu.iuh.fit.thanhtuyen.labweek2.frontend.models.EmployeeModel;
import vn.edu.iuh.fit.thanhtuyen.labweek2.frontend.models.ProductModel;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoginController", urlPatterns = "/login")
public class LoginController extends HttpServlet {
    @EJB
    private EmployeeModel employeeModel;
    @EJB
    private ProductModel productModel;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        String message = "";
        if (action.equals("login")) {
            String phone = req.getParameter("phone");
            if (phone == null || phone.isEmpty()) {
                message = "Phone is required!";
                req.setAttribute("message", message);
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            }
            EmployeeDto employee;
            employee = employeeModel.getEmployeeByPhone(phone);
            if (employee != null) {
                session.setAttribute("employee", employee);
                resp.sendRedirect(req.getContextPath() + "/products");
            }
        } else if (action.equals("logout")) {
            session.removeAttribute("employee");
            session.removeAttribute("cart");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }
}
