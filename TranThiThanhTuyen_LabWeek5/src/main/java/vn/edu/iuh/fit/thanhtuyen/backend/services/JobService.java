package vn.edu.iuh.fit.thanhtuyen.backend.services;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.JobDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.JobSkillDto;
import vn.edu.iuh.fit.thanhtuyen.backend.dtos.PageDTO;

import java.util.List;

public interface JobService {

    /**
     * Lấy thông tin công việc theo id
     * @param id: id của công việc
     * @return Thông tin công việc
     */
    JobDto getJobById(Long id);

    /**
     * Lấy danh sách công việc theo tên
     * @param name: Tên công việc
     * @return Danh sách công việc
     */
    PageDTO<JobDto> getJobsByName(String name, int page, int size);

    /**
     * Lấy danh sách công việc với paging, mỗi trang có số lượng công việc là size
     * @param page: Trang cần lấy
     * @param size: Số lượng công việc trên mỗi trang
     * @return Danh sách công việc
     */
    PageDTO<JobDto> getJobsPaging(int page, int size);

    /**
     * Lấy danh sách công việc phù hợp với candidate
     * @param candidateId: id của candidate
     * @param per: % phù hợp
     * @param page: Trang cần lấy
     * @param size: Số lượng công việc trên mỗi trang
     * @return Danh sách công việc
     */
    PageDTO<JobDto> getJobsMatchingCandidate(Long candidateId, int per, int page, int size);

    /**
     * Lấy danh sách công việc theo id của công ty
     * @param companyId: id của công ty
     * @param page: Trang cần lấy
     * @param size: Số lượng công việc trên mỗi trang
     * @return Danh sách công việc
     */
    PageDTO<JobDto> getJobsByCompanyId(Long companyId, int page, int size);

    /**
     * Lấy danh sách công việc theo tên công ty và tên công việc
     * @param companyId: id của công ty
     * @param name: Tên công việc
     * @param page: Trang cần lấy
     * @param size: Số lượng công việc trên mỗi trang
     * @return Danh sách công việc
     */
    PageDTO<JobDto> getJobsByCompanyAndName(Long companyId, String name, int page, int size);

    /**
     * Lưu công việc
     * @param jobDto: Thông tin công việc
     * @return Thông tin công việc
     */
    JobDto saveJob(JobDto jobDto);

    /**
     * Xóa kỹ năng của công việc
     * @param jobId Id của công việc
     * @param skillId Id của kỹ năng
     * @return true nếu xóa thành công, ngược lại false
     */
    boolean removeJobSkill(Long jobId, Long skillId);

    /**
     * Thêm kỹ năng cho công việc
     * @param jobSkillDto Thông tin kỹ năng
     * @return Thông tin kỹ năng
     */
    JobSkillDto addJobSkill(JobSkillDto jobSkillDto);
}
