package vn.edu.iuh.fit.student.thanhtuyen.backend.dtos;

import lombok.*;


import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link vn.edu.iuh.fit.student.thanhtuyen.backend.entities.PostComment}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostCommentDto implements Serializable {
    Long id;
    Long postId;
    Long parentId;
    String title;
    Boolean published;
    Instant createdAt;
    Instant publishedAt;
    String content;
    UserDto user;
}