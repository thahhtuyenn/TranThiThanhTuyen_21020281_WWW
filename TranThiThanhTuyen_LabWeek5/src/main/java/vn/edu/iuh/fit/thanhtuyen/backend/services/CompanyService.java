package vn.edu.iuh.fit.thanhtuyen.backend.services;

import vn.edu.iuh.fit.thanhtuyen.backend.dtos.CompanyDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.MailDto;

import java.util.List;

public interface CompanyService {
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
    CompanyDto saveCompany(CompanyDto company);

    /**
     * Lấy danh sách mail theo id công ty
     * @param id: id của công ty
     * @return danh sách mail
     */
    List<MailDto> getMailByCompany(Long id);
}
