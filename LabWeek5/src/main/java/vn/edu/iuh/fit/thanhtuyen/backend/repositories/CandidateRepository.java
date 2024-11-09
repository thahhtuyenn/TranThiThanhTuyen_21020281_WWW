package vn.edu.iuh.fit.thanhtuyen.backend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.thanhtuyen.backend.models.Candidate;
import vn.edu.iuh.fit.thanhtuyen.backend.models.Job;

import javax.ws.rs.QueryParam;
import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long>{
    @Query("SELECT c FROM Candidate c WHERE c.email = ?1 AND c.phone = ?2")
    Candidate findByEmailAndPhone(String email, String phone);

    @Query(
            "select c from Candidate c join c.candidateSkills cs "
                    + "join JobSkill js on js.skill.id = cs.skill.id "
                    + "where js.job.id = :jobId and cs.skillLevel >= js.skillLevel "
                    + "group by c.id "
                    + "having count(distinct cs.skill.id) * 100 / (select count(distinct jss.skill.id) from JobSkill jss where jss.job.id = :jobId) >= :per "
                    + "order by count(distinct cs.skill.id) * 100 / (select count(distinct jss.skill.id) from JobSkill jss where jss.job.id = :jobId) desc"
    )
    Page<Candidate> findCandidateMatchingJob(@QueryParam("jobId") Long jobId, @QueryParam("per") int per, PageRequest pageRequest);
}
