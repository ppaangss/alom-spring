package com.example.demo.like;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikeResponseDto {
    private Long id;
    private String username;
    private String boardTitle;
    private LocalDateTime likedAt;
}
