package vn.edu.iuh.fit.thanhtuyen.labweek2.frontend.models;

import jakarta.ejb.Remote;
import vn.edu.iuh.fit.thanhtuyen.labweek2.backend.dtos.CustomerDto;

import java.util.List;

@Remote
public interface CustomerModel {
    /**
     * Lay danh sach cac khach hang co san
     * @return danh sach cac khach hang
     */
    List<CustomerDto> getCustomers();

    /**
     * Lay thong tin khach hang theo so dien thoai
     * @param phone: so dien thoai cua khach hang
     * @return thong tin khach hang
     */
    CustomerDto getCustomerByPhone(String phone);

    CustomerDto createCustomer(CustomerDto customer);
}
