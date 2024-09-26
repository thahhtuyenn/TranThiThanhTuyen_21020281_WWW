package vn.edu.iuh.fit.thanhtuyen.labweek2.services;

import vn.edu.iuh.fit.thanhtuyen.labweek2.entities.Employee;
import vn.edu.iuh.fit.thanhtuyen.labweek2.enums.EmployeeStatus;

import java.util.List;

public interface EmployeeService {
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
    Employee findById(Long id);

    /**
     * Luu nhan vien
     * - Neu nhan vien da ton tai thi cap nhat
     * - Neu nhan vien chua ton tai thi tao moi
     * @param employee: thong tin nhan vien
     * @return nhan vien da duoc luu
     */
    Employee save(Employee employee);

    /**
     * Xoa nhan vien theo id
     * @param id id cua nhan vien
     * - Neu nhan vien ton tai va khong bi rang buoc voi bang khac thi xoa
     *           va tra ve true
     * - Neu nhan vien ton tai nhung bi rang buoc voi bang khac thi khong xoa va cap nhat status = -1
     * - Neu nhan vien khong ton tai thi tra ve false
     */
    boolean delete(Long id);

    /**
     * Tim nhan vien theo trang thai
     * @param status trang thai cua nhan vien (ACTIVE: 1, INACTIVE: 0, TERMINATED: -1)
     * @return danh sach nhan vien
     */
    List<Employee> findByStatus(int status);
}
