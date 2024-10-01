package vn.edu.iuh.fit.thanhtuyen.labweek2.backend.repositories;

import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.entities.OrderDetail;

import java.util.List;

public interface OrderDetailRepository {

    /**
     * Them danh sach cac chi tiet don hang
     * - Chi tiet nao ton tai trong CSDL thi cap nhat lai
     * - Chi tiet nao chua ton tai thi them moi
     * @param orderDetails: danh sach cac chi tiet don hang
     * @return danh sach cac chi tiet don hang da duoc them
     */
    List<OrderDetail> saveAll(List<OrderDetail> orderDetails);
}
