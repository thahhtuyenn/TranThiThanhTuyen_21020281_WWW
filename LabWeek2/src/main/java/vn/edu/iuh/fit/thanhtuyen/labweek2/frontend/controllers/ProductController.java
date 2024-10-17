package vn.edu.iuh.fit.thanhtuyen.labweek2.frontend.controllers;

import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.dtos.EmployeeDto;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.dtos.ProductDto;
import vn.edu.iuh.fit.thanhtuyen.labweek2.frontend.entities.Cart;
import vn.edu.iuh.fit.thanhtuyen.labweek2.frontend.entities.CartDetail;
import vn.edu.iuh.fit.thanhtuyen.labweek2.frontend.models.ProductModel;
import vn.edu.iuh.fit.thanhtuyen.labweek2.frontend.models.ShoppingCartModel;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductController", urlPatterns = "/products")
public class ProductController extends HttpServlet {
    @EJB
    private ProductModel productModel;
    @Inject
    private ShoppingCartModel shoppingCartModel;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProductDto> products = productModel.getProducts();
        HttpSession session = req.getSession();
        String action = req.getParameter("action");
        EmployeeDto employee = (EmployeeDto) session.getAttribute("employee");
        if (action != null) {
            if (action.equalsIgnoreCase("addToCart")) {
                CartDetail cartDetail = new CartDetail();
                String productId = req.getParameter("productId");
                ProductDto product = productModel.getProductById(Long.parseLong(productId));
                if (product != null){
                    String qty = req.getParameter("quantity");
                    double quantity = 1;
                    if (qty != null && !qty.isEmpty()) {
                        quantity = Double.parseDouble(qty);
                    }
                    cartDetail.setProduct(product);
                    cartDetail.setQuantity(quantity);
                    cartDetail.setPrice(product.getPrice());
                    shoppingCartModel.addToCart(cartDetail, session);
                }
            }else if(action.equalsIgnoreCase("removeFromCart")){
                String productId = req.getParameter("productId");
                ProductDto product = productModel.getProductById(Long.parseLong(productId));
                if (product != null){
                    shoppingCartModel.removeFromCart(product, session);
                }
            }
        }
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {
            employee = cart.getEmployee();
        }
        req.setAttribute("cart", cart);
        req.setAttribute("employee", employee);
        req.setAttribute("products", products);
        req.getRequestDispatcher("views/products.jsp").forward(req, resp);
    }
}
