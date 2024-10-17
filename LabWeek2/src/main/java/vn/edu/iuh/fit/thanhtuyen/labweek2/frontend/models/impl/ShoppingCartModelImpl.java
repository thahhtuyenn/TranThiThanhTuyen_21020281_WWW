package vn.edu.iuh.fit.thanhtuyen.labweek2.frontend.models.impl;

import jakarta.servlet.http.HttpSession;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.dtos.EmployeeDto;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.dtos.ProductDto;
import vn.edu.iuh.fit.thanhtuyen.labweek2.frontend.entities.Cart;
import vn.edu.iuh.fit.thanhtuyen.labweek2.frontend.entities.CartDetail;
import vn.edu.iuh.fit.thanhtuyen.labweek2.frontend.models.ShoppingCartModel;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartModelImpl implements ShoppingCartModel {
    @Override
    public Cart getCart(HttpSession session) {
        return session.getAttribute("cart") == null ? new Cart() : (Cart) session.getAttribute("cart");
    }

    @Override
    public void addToCart(CartDetail cartDetail, HttpSession session) {
        EmployeeDto employee = (EmployeeDto) session.getAttribute("employee");
        Cart cart = getCart(session);
        cart.setEmployee(employee);
        List<CartDetail> cartDetails = cart.getCartDetails();
        if (cartDetails == null) {
            cartDetails = new ArrayList<>();
        }
        boolean isExist = false;

        for (CartDetail c : cartDetails) {
            if (c.getProduct().getId() .equals(cartDetail.getProduct().getId())){
                double qty = c.getQuantity() + cartDetail.getQuantity();
                c.setQuantity(qty);
                isExist = true;
            }
        }

        if (!isExist){
            cartDetails.add(cartDetail);
        }

        cart.setCartDetails(cartDetails);
        session.setAttribute("cart", cart);
    }

    @Override
    public void removeFromCart(ProductDto product, HttpSession session) {
        Cart cart = getCart(session);
        List<CartDetail> cartDetails = cart.getCartDetails();
        if (cartDetails != null) {
            for (CartDetail c : cartDetails) {
                if (c.getProduct().getId().equals(product.getId())) {
                    cartDetails.remove(c);
                    break;
                }
            }
        }
        cart.setCartDetails(cartDetails);
        session.setAttribute("cart", cart);
    }
}