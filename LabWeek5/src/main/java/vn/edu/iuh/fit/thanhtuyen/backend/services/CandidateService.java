package vn.edu.iuh.fit.thanhtuyen.backend.services;

import org.springframework.data.domain.Page;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.CandidateDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.PageDTO;

import java.util.List;

public interface CandidateService {
    /**
     * Lấy danh sách tất cả ứng viên
     * @return danh sách ứng viên
     */
    List<CandidateDto> getAll();

    /**
     * Lấy thông tin ứng viên theo id
     * @param id: id ứng viên
     * @return thông tin ứng viên có id tương ứng
     */
    CandidateDto getById(Long id);

    /**
     * Lưu thông tin ứng viên
     * - Nếu id đã tồn tại thì cập nhật thông tin ứng viên
     * - Nếu id chưa tồn tại thì thêm mới ứng viên
     * @param candidateDto: thông tin ứng viên
     * @return thông tin ứng viên đã được lưu
     */
    CandidateDto save(CandidateDto candidateDto);

    /**
     * Lấy thông tin ứng viên với paging, mỗi trang có số lượng ứng viên là size
     * @param page: trang cần lấy
     * @param size: số lượng ứng viên trên mỗi trang
     * @return danh sách ứng viên trên trang cần lấy
     */
    List<CandidateDto> getCandidatePaging(int page, int size);

    /**
     * Lấy thông tin ứng viên theo email và phone
     * @param email: email ứng viên
     * @param phone: số điện thoại ứng viên
     * @return thông tin ứng viên có email và phone tương ứng
     */
    CandidateDto findByEmailAndPhone(String email, String phone);

    /**
     * Lấy danh sách ứng viên phù hợp với công việc theo id công việc và % phù hợp
     * @param jobId: id công việc
     * @param per: % phù hợp
     * @return danh sách ứng viên phù hợp với công việc
     */
    PageDTO<CandidateDto> findCandidateMatchingJob(Long jobId, int per, int page, int size);
}
