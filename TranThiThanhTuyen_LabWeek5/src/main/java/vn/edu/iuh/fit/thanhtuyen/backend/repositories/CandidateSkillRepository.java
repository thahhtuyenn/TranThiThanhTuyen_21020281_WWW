package vn.edu.iuh.fit.thanhtuyen.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.thanhtuyen.backend.models.CandidateSkill;

import java.util.Optional;

@Repository
public interface CandidateSkillRepository extends JpaRepository<CandidateSkill, Long>{
    @Modifying
    @Query("DELETE FROM CandidateSkill c WHERE c.id.canId = ?1 AND c.id.skillId = ?2")
    void removeByCandidateIdAndSkillId(Long candidateId, Long skillId);
    @Query("SELECT c FROM CandidateSkill c WHERE c.id.canId = ?1 AND c.id.skillId = ?2")
    Optional<CandidateSkill> findByCandidateIdAndSkillId(Long candidateId, Long skillId);
}
