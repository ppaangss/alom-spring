package com.example.demo.join.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JoinLoginRequest {
    private String email;
    private String password;
}
