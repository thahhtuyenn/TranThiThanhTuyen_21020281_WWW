package vn.edu.iuh.fit.thanhtuyen.labweek2.backend.repositories;

import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.entities.Employee;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.enums.EmployeeStatus;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
    /**
     * Tim tat ca nhan vien
     * @return danh sach nhan vien
     */
    List<Employee> findAll();

    /**
     * Tim nhan vien theo id
     * @param id id cua nhan vien
     * @return nhan vien
     */
    Optional<Employee> findById(Long id);

    /**
     * Luu nhan vien
     * - Neu nhan vien da ton tai thi cap nhat
     * - Neu nhan vien chua ton tai thi tao moi
     * @param employee: thong tin nhan vien
     * @return nhan vien da duoc luu
     */
    Employee save(Employee employee);

    /**
     * Cap nhat trang thai cua nhan vien
     * @param id id cua nhan vien
     * @param status trang thai moi cua nhan vien
     * @return nhan vien da duoc cap nhat
     */
    Employee updateStatus(Long id, EmployeeStatus status);

    /**
     * Tim nhan vien theo trang thai
     * @param status trang thai cua nhan vien (ACTIVE: 1, INACTIVE: 0, TERMINATED: -1)
     * @return danh sach nhan vien
     */
    List<Employee> findByStatus(EmployeeStatus status);
}
