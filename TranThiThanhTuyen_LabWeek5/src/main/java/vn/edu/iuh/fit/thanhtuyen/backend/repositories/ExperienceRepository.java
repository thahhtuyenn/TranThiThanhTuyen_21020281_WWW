package vn.edu.iuh.fit.thanhtuyen.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.thanhtuyen.backend.models.Experience;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Long> {
}
