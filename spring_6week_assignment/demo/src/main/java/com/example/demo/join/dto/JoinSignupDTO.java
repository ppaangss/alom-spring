package com.example.demo.join.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JoinSignupDTO {
    private String email;
    private String password;
    private String nickname;
}
