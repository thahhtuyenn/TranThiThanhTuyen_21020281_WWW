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
import vn.edu.iuh.fit.thanhtuyen.labweek2.frontend.entities.Cart;
import vn.edu.iuh.fit.thanhtuyen.labweek2.frontend.models.ProductModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "ProductController", urlPatterns = "/products")
public class ProductController extends HttpServlet {
    @EJB
    private ProductModel productModel;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProductDto> products = productModel.getProducts();
        HttpSession session = req.getSession();
        EmployeeDto employee = (EmployeeDto) session.getAttribute("employee");
        String action = req.getParameter("action");
        List<Cart> shoppingCart = (List<Cart>) session.getAttribute("cart");
        if (action != null) {
            if (action.equalsIgnoreCase("addToCart")) {
                String productId = req.getParameter("productId");
                ProductDto product = productModel.getProductById(Long.parseLong(productId));
                if (shoppingCart == null) {
                    shoppingCart = new ArrayList<>();
                }
                if (product != null) {
                    String qty = req.getParameter("quantity");
                    int quantity = 1;
                    if (qty != null && !qty.isEmpty()) {
                        quantity = Integer.parseInt(qty);
                    }

                    boolean isExist = false;
                    if (!shoppingCart.isEmpty()) {
                        for (Cart cart : shoppingCart) {
                            if (Objects.equals(cart.getProductId(), product.getId())) {
                                cart.setQuantity(cart.getQuantity() + quantity);
                                isExist = true;
                                break;
                            }
                        }
                    }
                    if (!isExist) {
                        Cart cart = new Cart();
                        cart.setProductId(product.getId());
                        cart.setProductName(product.getName());
                        cart.setPrice(product.getPrice());
                        cart.setQuantity(quantity);
                        shoppingCart.add(cart);
                    }
                    session.setAttribute("cart", shoppingCart);
                }
            }
        }
        req.setAttribute("employee", employee);
        req.setAttribute("products", products);
        req.setAttribute("cart", shoppingCart);
        req.getRequestDispatcher("views/products.jsp").forward(req, resp);
    }
}
