package com.example.ticketing.api.member.service;

import com.example.ticketing.api.member.dto.req.LoginReqDto;
import com.example.ticketing.api.member.dto.req.SignUpReqDto;
import com.example.ticketing.api.member.entity.Member;
import com.example.ticketing.api.member.repository.MemberRepository;
import com.example.ticketing.common.exception.CustomException;
import com.example.ticketing.common.exception.FailMessage;
import com.example.ticketing.common.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    /**
     회원가입
     */
    public Long createAccount(SignUpReqDto signUpReqDto) {

        if(memberRepository.existsByEmail(signUpReqDto.getEmail())){
            throw new CustomException(FailMessage.CONFLICT_DUPLICATE_ID);
        }
        Member member= Member.builder()
                .email(signUpReqDto.getEmail())
                .password(passwordEncoder.encode(signUpReqDto.getPassword()))
                .build();
        return memberRepository.save(member).getId();
    }

    /**
     * 로그인
     */
    public String login(LoginReqDto loginReqDto) {
        Member member=memberRepository.findByEmail(loginReqDto.getEmail()).orElseThrow(()->
                new CustomException(FailMessage.CONFLICT_NO_ID));

        if (!passwordEncoder.matches(loginReqDto.getPassword(), member.getPassword())){
            throw new CustomException(FailMessage.CONFLICT_WRONG_PW);
        }
        return jwtTokenUtil.createToken(member.getEmail());
    }
}
