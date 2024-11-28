package vn.edu.iuh.fit.thanhtuyen.backend.services;

import vn.edu.iuh.fit.thanhtuyen.backend.dtos.CompanyDto;

import java.util.List;

public interface CompanyService {
    /**
     * Lấy danh sách tất cả các công ty
     * @return danh sách công ty
     */
    List<CompanyDto> getAllCompany();

    /**
     * Lấy thông tin công ty theo id
     * @param id: id của công ty
     * @return thông tin công ty
     */
    CompanyDto getCompanyById(Long id);

    /**
     * Lưu thông tin công ty
     * - Nếu id != null thì cập nhật thông tin công ty
     * - Nếu id == null thì thêm mới công ty
     * @param company: thông tin công ty
     * @return true nếu lưu thành công, false nếu lưu thất bại
     */
    boolean saveCompany(CompanyDto company);
}
