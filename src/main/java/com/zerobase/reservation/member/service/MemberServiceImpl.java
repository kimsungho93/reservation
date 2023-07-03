package com.zerobase.reservation.member.service;

import com.zerobase.reservation.member.entity.Member;
import com.zerobase.reservation.member.exception.BizException;
import com.zerobase.reservation.member.model.LoginForm;
import com.zerobase.reservation.member.model.MemberCode;
import com.zerobase.reservation.member.model.MemberDto;
import com.zerobase.reservation.member.model.MemberInput;
import com.zerobase.reservation.member.repository.MemberRepository;
import com.zerobase.reservation.util.PasswordUtils;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.zerobase.reservation.member.model.MemberCode.*;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public boolean register(MemberInput parameter) {
        Optional<Member> optionalMember = memberRepository.findByUserId(parameter.getUserId());
        // 동일한 userId가 이미 있는 경우 가입 실패
        if (optionalMember.isPresent()) {
            return false;
        }

        String encPassword = BCrypt.hashpw(parameter.getPassword(), BCrypt.gensalt());

        Member member = Member.builder()
                .userId(parameter.getUserId())
                .password(encPassword)
                .userName(parameter.getUserName())
                .phone(parameter.getPhone())
                .role(parameter.getRole())
                .status(MEMBER_STATUS_ING)
                .regDate(LocalDateTime.now())
                .build();

        memberRepository.save(member);
        return true;
    }

    @Override
    public Member login(LoginForm parameter) {
        Optional<Member> optionalMember = memberRepository.findByUserId(parameter.getUserId());
        if (optionalMember.isEmpty()) {
            throw new BizException("회원 정보가 존재하지 않습니다.");
        }

        Member member = optionalMember.get();

        if (!PasswordUtils.equalPassword(parameter.getPassword(), member.getPassword())) {
            throw new BizException("일치하는 정보가 없습니다.");
        }

        return member;
    }

    @Override
    public MemberDto detail(long id) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if (optionalMember.isEmpty()) {
            throw new BizException("회원 정보가 존재하지 않습니다.");
        }

        Member member = optionalMember.get();

        return MemberDto.builder()
                .id(member.getId())
                .userId(member.getUserId())
                .userName(member.getUserName())
                .phone(member.getPhone())
                .role(member.getRole())
                .regDate(member.getRegDate())
                .build();
    }
}
