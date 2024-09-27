package vn.edu.iuh.fit.thanhtuyen.labweek2.services;

import vn.edu.iuh.fit.thanhtuyen.labweek2.dtos.CustomerDto;
import vn.edu.iuh.fit.thanhtuyen.labweek2.entities.Customer;

import java.util.List;

public interface CustomerService {
    /**
     * Tim tat ca khach hang
     * @return danh sach khach hang
     */
    List<CustomerDto> findAll();

    /**
     * Tim khach hang theo id
     * @param id id cua khach hang
     * @return khach hang
     */
    CustomerDto findById(Long id);

    /**
     * Luu khach hang
     * - Neu khach hang da ton tai thi cap nhat
     * - Neu khach hang chua ton tai thi tao moi
     * @param customer: thong tin khach hang
     * @return khach hang da duoc luu
     */
    CustomerDto save(CustomerDto customer);

    /**
     * Xoa khach hang theo id
     * @param id id cua khach hang
     * @return true neu xoa thanh cong, false neu khong xoa duoc
     */
    boolean delete(Long id);
}
