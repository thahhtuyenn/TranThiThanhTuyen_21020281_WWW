package vn.edu.iuh.fit.thanhtuyen.labweek2.frontend.models;

import jakarta.servlet.http.HttpSession;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.dtos.ProductDto;
import vn.edu.iuh.fit.thanhtuyen.labweek2.frontend.entities.Cart;
import vn.edu.iuh.fit.thanhtuyen.labweek2.frontend.entities.CartDetail;

import java.util.List;

public interface ShoppingCartModel {
    Cart getCart(HttpSession session);
    void addToCart(CartDetail cartDetail, HttpSession session);
    void removeFromCart(ProductDto product, HttpSession session);
}
