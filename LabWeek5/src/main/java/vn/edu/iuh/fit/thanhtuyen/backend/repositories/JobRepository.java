package vn.edu.iuh.fit.thanhtuyen.backend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.thanhtuyen.backend.models.Job;

import javax.ws.rs.QueryParam;
import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    /**
     * Lọc ra các job phù hợp với candidate theo số % phù hợp của các skill candidate so với job đó
     * - Lấy ra các job bao gồm skill
     * - Lấy ra các skill của candidate
     * - Lọc ra các skill của candidate phù hợp với job
     * - Nhóm các job lại theo id để lấy ra các job không trùng nhau
     * - Tính số lượng skill không trùng của candidate * 100 / số lượng skill không trùng của job
     * - Sắp xếp các job theo % phù hợp giảm dần
     * @param candidateId : id của candidate cần tìm job
     * @param per         : % phù hợp của các skill candidate so với job đó
     * @return : danh sách job phù hợp
     */
    @Query(
            "select j from  Job j join  j.jobSkills js "
                    + "join  CandidateSkill cs on cs.skill.id = js.skill.id "
                    + "where cs.candidate.id = :candidateId and cs.skillLevel >= js.skillLevel "
                    + "group by j.id "
                    + "having count(distinct cs.skill.id) * 100 / (select count(distinct jss.skill.id) from JobSkill jss where jss.job.id = j.id) >= :per "
                    + "order by count(distinct cs.skill.id) * 100 / (select count(distinct jss.skill.id) from JobSkill jss where jss.job.id = j.id) desc"
    )
    Page<Job> findJobsForCandidateWithLevel(@QueryParam("candidateId") Long candidateId, @QueryParam("per") int per, PageRequest pageRequest);





    @Query("select j from Job j where j.jobName like %:jobName%")
    Page<Job> findByJobName(@QueryParam("jobName") String jobName, PageRequest pageRequest);

    @Query("SELECT CEIL(COUNT(j) / :size) FROM Job j")
    int countPageJobs(@QueryParam("size") int size);

    @Query("select j from Job j where j.company.id = :companyId")
    Page<Job> findByCompanyId(@QueryParam("companyId") Long companyId, PageRequest pageRequest);

    @Query("select j from Job j where j.company.id = :companyId and j.jobName like %:jobName%")
    Page<Job> findByCompanyAndName(@QueryParam("companyId") Long companyId, @QueryParam("jobName") String jobName, PageRequest pageRequest);
}
