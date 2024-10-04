package vn.edu.iuh.fit.thanhtuyen.labweek2.frontend.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.dtos.EmployeeDto;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.dtos.ProductDto;
import vn.edu.iuh.fit.thanhtuyen.labweek2.frontend.entities.Cart;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShoppingCartController", urlPatterns = "/shopping-cart")
public class ShoppingCartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        List<Cart> shoppingCart = (List<Cart>) session.getAttribute("cart");
        if(action != null){
            if(action.equalsIgnoreCase("deleteProduct")){
                String productId = req.getParameter("productId");
                Long id = Long.parseLong(productId);
                Cart cartRemove = shoppingCart.stream().filter(cart -> cart.getProductId().equals(id)).findFirst().orElse(null);
                if (cartRemove != null) {
                    shoppingCart.remove(cartRemove);
                }
            }
        }

        req.setAttribute("cart", shoppingCart);
        EmployeeDto employee = (EmployeeDto) session.getAttribute("employee");
        req.setAttribute("employee", employee);
        req.getRequestDispatcher("views/shoppingCart.jsp").forward(req, resp);

    }
}
