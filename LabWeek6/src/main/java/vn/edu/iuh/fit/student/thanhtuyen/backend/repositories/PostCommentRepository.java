package vn.edu.iuh.fit.student.thanhtuyen.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.student.thanhtuyen.backend.entities.PostComment;

@Repository
public interface PostCommentRepository extends JpaRepository<PostComment, Long> {
}
