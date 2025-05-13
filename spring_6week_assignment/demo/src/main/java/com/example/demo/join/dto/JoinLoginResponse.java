package com.example.demo.join.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JoinLoginResponse {
    private String email;
    private String nickname;
}
