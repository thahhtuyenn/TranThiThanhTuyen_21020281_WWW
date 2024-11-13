package vn.edu.iuh.fit.student.thanhtuyen.backend.dtos;

import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

/**
 * DTO for {@link vn.edu.iuh.fit.student.thanhtuyen.backend.entities.Post}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostDto implements Serializable {
    Long id;
    PostDto parent;
    String title;
    String metaTitle;
    String summary;
    Boolean published;
    Instant createdAt;
    Instant updatedAt;
    Instant publishedAt;
    String content;
    Set<PostCommentDto> postComments;
    UserDto author;
}