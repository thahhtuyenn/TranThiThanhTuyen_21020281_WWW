package vn.edu.iuh.fit.thanhtuyen.backend.dtos;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MailDto implements Serializable {
    Long id;
    String content;
    Long candidateId;
    String subject;
    String to;
    Long jobId;
}
