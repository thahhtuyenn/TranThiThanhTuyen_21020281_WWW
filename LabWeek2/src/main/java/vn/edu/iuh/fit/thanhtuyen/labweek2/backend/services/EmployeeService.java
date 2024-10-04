package vn.edu.iuh.fit.thanhtuyen.labweek2.backend.services;

import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.dtos.EmployeeDto;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.enums.EmployeeStatus;

import java.util.List;

public interface EmployeeService {
    /**
     * Tim tat ca nhan vien
     * @return danh sach nhan vien
     */
    List<EmployeeDto> findAll();

    /**
     * Tim nhan vien theo id
     * @param id id cua nhan vien
     * @return nhan vien
     */
    EmployeeDto findById(Long id);

    /**
     * Luu nhan vien
     * - Neu nhan vien da ton tai thi cap nhat
     * - Neu nhan vien chua ton tai thi tao moi
     * @param employee: thong tin nhan vien
     * @return nhan vien da duoc luu
     */
    EmployeeDto save(EmployeeDto employee);

    /**
     * Tim nhan vien theo trang thai
     * @param status trang thai cua nhan vien (ACTIVE: 1, INACTIVE: 0, TERMINATED: -1)
     * @return danh sach nhan vien
     */
    List<EmployeeDto> findByStatus(EmployeeStatus status);

    /**
     * Cap nhat trang thai nhan vien
     * @param id id cua nhan vien
     * @param status trang thai moi
     * @return nhan vien da duoc cap nhat
     */
    EmployeeDto updateStatus(Long id, EmployeeStatus status);

    /**
     * Tim nhan vien theo so dien thoai
     * @param phone so dien thoai
     * @return nhan vien
     */
    EmployeeDto findByPhone(String phone);
}
