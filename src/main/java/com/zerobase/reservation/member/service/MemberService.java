package com.zerobase.reservation.member.service;


import com.zerobase.reservation.member.entity.Member;
import com.zerobase.reservation.member.model.LoginForm;
import com.zerobase.reservation.member.model.MemberDto;
import com.zerobase.reservation.member.model.MemberInput;

public interface MemberService {
    /**
     * 회원 등록
     */
    boolean register(MemberInput parameter);

    /**
     * 회원 로그인
     */
    Member login(LoginForm parameter);

    MemberDto detail(long id);
}
