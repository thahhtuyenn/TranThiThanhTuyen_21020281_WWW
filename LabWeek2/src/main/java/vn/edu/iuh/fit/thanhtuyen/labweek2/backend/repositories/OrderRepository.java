package vn.edu.iuh.fit.thanhtuyen.labweek2.backend.repositories;

import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.entities.Order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    /**
     * Lay dannh sach tat ca cac don hang
     * @return danh sach cac don hang
     */
    List<Order> findAll();

    /**
     * Tim don hang theo ma so
     * @param id ma so don hang
     * @return don hang co ma so id
     */
    Optional<Order> findById(Long id);

    /**
     * Them moi hoac cap nhat don hang
     * - Neu don hang co ma so id da ton tai thi cap nhat
     * - Neu don hang chua co ma so id thi them moi
     * @param order don hang can them moi hoac cap nhat
     * @return don hang sau khi da them moi hoac cap nhat
     */
    Order save(Order order);

    /**
     * Tim don hang theo ma so nhan vien
     * @param employeeId: ma so nhan vien
     * @return danh sach don hang cua nhan vien co ma so employeeId
     */
    List<Order> findByEmployeeId(Long employeeId);
}
