package vn.edu.iuh.fit.thanhtuyen.labweek2.backend.services;

import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.dtos.OrderDto;

import java.util.List;

public interface OrderService {
    /**
     * Lấy danh sách tat ca các order
     * @return danh sách order
     */
    List<OrderDto> findAll();

    /**
     * Lấy order theo id
     * @param id: ma hoa don
     * @return order
     */
    OrderDto findById(Long id);

    /**
     * Lưu order
     * - Nếu order có id thì cập nhật
     * - Nếu order không có id thì thêm mới
     * @param order: order
     * @return order
     */
    OrderDto save(OrderDto order);
 }
