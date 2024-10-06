package vn.edu.iuh.fit.thanhtuyen.labweek2.frontend.models;

import jakarta.ejb.Remote;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.dtos.OrderDto;

import java.util.List;

@Remote
public interface OrderModel {
    /**
     * Lay danh sach tat ca hoa don theo nhan vien
     * @return danh sach hoa don
     */
    List<OrderDto> getOrders(Long employeeId);

    /**
     * Tao moi hoa don
     * @param order: thong tin hoa don
     * @return hoa don moi duoc tao
     */
    OrderDto createOrder(OrderDto order);
}