package vn.edu.iuh.fit.thanhtuyen.labweek2.repositories;

import vn.edu.iuh.fit.thanhtuyen.labweek2.models.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    /**
     * Tim tat ca khach hang
     * @return danh sach khach hang
     */
    List<Customer> findAll();

    /**
     * Tim khach hang theo id
     * @param id id cua khach hang
     * @return khach hang
     */
    Optional<Customer> findById(Long id);

    /**
     * Luu khach hang
     * - Neu khach hang da ton tai thi cap nhat
     * - Neu khach hang chua ton tai thi tao moi
     * @param customer: thong tin khach hang
     * @return khach hang da duoc luu
     */
    Customer save(Customer customer);

    /**
     * Xoa khach hang theo id
     * @param id id cua khach hang
     */
    boolean delete(Long id);
}
