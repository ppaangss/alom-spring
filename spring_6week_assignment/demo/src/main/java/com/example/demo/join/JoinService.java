package com.example.demo.join;

import com.example.demo.join.dto.JoinLoginRequest;
import com.example.demo.join.dto.JoinLoginResponse;
import com.example.demo.join.dto.JoinSignupDTO;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import lombok.Builder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Builder
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public JoinService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {

        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void signup(JoinSignupDTO joinSignupDTO) {
        //회원가입

        Boolean isExist = userRepository.existsByEmail(joinSignupDTO.getEmail());
        if (isExist) {
            throw new RuntimeException("중복 이메일");
        }

        User data = new User();
        data.setUsername(joinSignupDTO.getNickname());
        data.setPassword(bCryptPasswordEncoder.encode(joinSignupDTO.getPassword()));
        data.setEmail(joinSignupDTO.getEmail());
        userRepository.save(data);
    }

    public JoinLoginResponse login(JoinLoginRequest joinLoginRequest) {
        Boolean isExist = userRepository.existsByEmail(joinLoginRequest.getEmail());
        if (!isExist) {
            throw new RuntimeException("이메일 없음");
        }

        if (!bCryptPasswordEncoder.matches(joinLoginRequest.getPassword(), joinLoginRequest.getPassword())) {
            throw new RuntimeException("비밀번호 불일치");
        }

        JoinLoginResponse userLoginResponse = new JoinLoginResponse();
        return new JoinLoginResponse(
                userLoginResponse.getEmail(),
                userLoginResponse.getNickname()
        );
    }
}