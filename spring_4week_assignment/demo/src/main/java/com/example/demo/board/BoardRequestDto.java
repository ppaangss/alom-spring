package com.example.demo.board;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardRequestDto {
    private String title;
    private String content;
    private Long authorId;
}
