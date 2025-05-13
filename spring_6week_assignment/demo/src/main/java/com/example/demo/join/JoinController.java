package com.example.demo.join;

import com.example.demo.join.dto.JoinLoginRequest;
import com.example.demo.join.dto.JoinLoginResponse;
import com.example.demo.join.dto.JoinSignupDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class JoinController {
    private final JoinService joinService;

    public JoinController(JoinService joinService) {
        this.joinService = joinService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signupProcess(JoinSignupDTO joinSignupDTO) {

        joinService.signup(joinSignupDTO);
        return ResponseEntity.ok("회원가입 성공");
    }

    @PostMapping("/login")
    public ResponseEntity<JoinLoginResponse> loginProcess(@RequestBody JoinLoginRequest joinLoginRequest) {
        JoinLoginResponse response = joinService.login(joinLoginRequest);
        return ResponseEntity.ok(response);
    }
}
