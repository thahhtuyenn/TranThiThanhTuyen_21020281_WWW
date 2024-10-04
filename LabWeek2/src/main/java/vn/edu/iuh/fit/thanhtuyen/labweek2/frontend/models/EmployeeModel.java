package vn.edu.iuh.fit.thanhtuyen.labweek2.frontend.models;

import jakarta.ejb.Remote;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.dtos.EmployeeDto;

@Remote
public interface EmployeeModel {

    /**
     * Tim kiem nhan vien theo so dien thoai
     * @param phone So dien thoai cua nhan vien
     * @return Thong tin nhan vien
     */
    EmployeeDto getEmployeeByPhone(String phone);

    /**
     * Cap nhat thong tin nhan vien
     * @param employee Thong tin nhan vien
     * @return Thong tin nhan vien sau khi cap nhat
     */
    EmployeeDto updateEmployee(EmployeeDto employee);
}
