package vn.edu.iuh.fit.thanhtuyen.labweek2.frontend.models;

import jakarta.ejb.Remote;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.dtos.CustomerDto;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.dtos.EmployeeDto;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.dtos.OrderDto;
import vn.edu.iuh.fit.thanhtuyen.labweek2.frontend.entities.Cart;

import java.util.List;

@Remote
public interface OrderModel {

    /**
     * Tao moi hoa don
     * @param cart: thong tin hoa don
     * @return hoa don moi duoc tao
     */
    OrderDto createOrder(Cart cart);


}