package vn.edu.iuh.fit.thanhtuyen.backend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.thanhtuyen.backend.models.Candidate;

import java.awt.print.Pageable;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long>{
    @Query("SELECT c FROM Candidate c WHERE c.email = ?1 AND c.phone = ?2")
    Candidate findByEmailAndPhone(String email, String phone);
}
