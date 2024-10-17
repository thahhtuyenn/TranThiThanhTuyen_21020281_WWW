package vn.edu.iuh.fit.thanhtuyen.labweek2.frontend.controllers;

import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.dtos.CustomerDto;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.dtos.EmployeeDto;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.dtos.OrderDto;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.dtos.ProductDto;
import vn.edu.iuh.fit.thanhtuyen.labweek2.frontend.entities.Cart;
import vn.edu.iuh.fit.thanhtuyen.labweek2.frontend.models.CustomerModel;
import vn.edu.iuh.fit.thanhtuyen.labweek2.frontend.models.OrderModel;
import vn.edu.iuh.fit.thanhtuyen.labweek2.frontend.models.ProductModel;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderController", urlPatterns = "/orders")
public class OrderController extends HttpServlet {
    @EJB
    private OrderModel orderModel;

    @EJB
    private CustomerModel customerModel;

    @EJB
    private ProductModel productModel;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String action = req.getParameter("action");
        Cart cart = (Cart) session.getAttribute("cart");
        EmployeeDto employee = (EmployeeDto) session.getAttribute("employee");
        List<CustomerDto> customers = customerModel.getCustomers();
        CustomerDto customer = null;
        if (action != null){
            if (action.equalsIgnoreCase("searchCustomer")) {
                String phone = req.getParameter("phone");
                customer = customerModel.getCustomerByPhone(phone);
                if (customer != null) {
                    session.setAttribute("customer", customer);
                    cart.setCustomer(customer);
                }
            }
        }

        req.setAttribute("customer", customer);
        req.setAttribute("customers", customers);
        req.setAttribute("employee", employee);
        req.setAttribute("cart", cart);
        req.getRequestDispatcher("views/orders.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        EmployeeDto employee = cart.getEmployee();
        if (action != null){
            if(action.equalsIgnoreCase("createCustomer")){
                String name = req.getParameter("name");
                String phone = req.getParameter("phoneCustomer");
                String address = req.getParameter("address");
                String email = req.getParameter("email");
                CustomerDto customer = new CustomerDto();
                customer.setName(name);
                customer.setPhone(phone);
                customer.setAddress(address);
                customer.setEmail(email);
                customer = customerModel.createCustomer(customer);
                if (customer != null){
                    cart.setCustomer(customer);
                    session.setAttribute("customer", customer);
                    req.setAttribute("cart", cart);
                    req.setAttribute("employee", employee);
                    req.setAttribute("customer", customer);
                    req.getRequestDispatcher("views/orders.jsp").forward(req, resp);
                }
            }else if(action.equalsIgnoreCase("createOrder")){
                OrderDto order = orderModel.createOrder(cart);
                if (order != null){
                    req.setAttribute("employee", employee);
                    session.setAttribute("employee", employee);
                    session.removeAttribute("cart");
                    List<ProductDto> products = productModel.getProducts();
                    req.setAttribute("products", products);
                    req.getRequestDispatcher("views/products.jsp").forward(req, resp);
                }
            }
        }


    }
}
