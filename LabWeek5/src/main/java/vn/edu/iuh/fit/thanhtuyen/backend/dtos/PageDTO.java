package vn.edu.iuh.fit.thanhtuyen.backend.dtos;


import lombok.*;

import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class PageDTO <T>{
    private int page;
    private int size;
    private int totalPages;

    private Collection<T> content;
}
