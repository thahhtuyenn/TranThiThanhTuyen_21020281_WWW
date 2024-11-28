package vn.edu.iuh.fit.thanhtuyen.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.iuh.fit.thanhtuyen.backend.models.JobSkill;
import vn.edu.iuh.fit.thanhtuyen.backend.models.JobSkillId;

import javax.ws.rs.QueryParam;

@Repository
public interface JobSkillRepository extends JpaRepository<JobSkill, Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM JobSkill j WHERE j.job.id = :jobId")
    void deleteByJobId(@Param("jobId") Long jobId);

    boolean existsByJobId(Long id);
}
