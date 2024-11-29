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
import java.util.Optional;

@Repository
public interface JobSkillRepository extends JpaRepository<JobSkill, Long> {
    @Modifying
    @Query("DELETE FROM JobSkill j WHERE j.id.jobId = ?1 AND j.id.skillId = ?2")
    void removeByJobIdAndSkillId(Long jobId, Long skillId);

    @Query("SELECT j FROM JobSkill j WHERE j.id.jobId = ?1 AND j.id.skillId = ?2")
    Optional<JobSkill> findByJobIdAndSkillId(Long jobId, Long skillId);
}
