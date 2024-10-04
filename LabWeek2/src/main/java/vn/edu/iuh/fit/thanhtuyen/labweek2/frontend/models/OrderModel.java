package vn.edu.iuh.fit.thanhtuyen.labweek2.frontend.models;

import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface OrderModel {
    /**
     * Lay danh sach tat ca hoa don theo nhan vien
     * @return danh sach hoa don
     */
    List<OrderModel> getOrders(Long employeeId);
}