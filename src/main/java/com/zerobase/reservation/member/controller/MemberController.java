package com.zerobase.reservation.member.controller;

import com.zerobase.reservation.member.entity.Member;
import com.zerobase.reservation.member.exception.BizException;
import com.zerobase.reservation.member.model.LoginForm;
import com.zerobase.reservation.member.model.MemberDto;
import com.zerobase.reservation.member.model.MemberInput;
import com.zerobase.reservation.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.http.HttpResponse;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/register")
    public String register() {
        return "member/register";
    }

    @GetMapping("/member/normal-form")
    public String normalRegister() {
        return "/member/normal-form";
    }

    @GetMapping("/member/partner-form")
    public String partnerRegister() {
        return "/member/partner-form";
    }

    @PostMapping("/member/addUser")
    public String addUser(Model model, @Valid MemberInput parameter) {
        boolean result = memberService.register(parameter);

        model.addAttribute("result", result);
        return "member/register-complete";
    }

    @PostMapping("/member/addPartner")
    public String addPartner(Model model, @Valid MemberInput parameter) {
        boolean result = memberService.register(parameter);

        model.addAttribute("result", result);
        return "member/register-complete";
    }

    @GetMapping("/member/login")
    public String login() {
        return "member/login";
    }

    @PostMapping("/member/login")
    public String login(LoginForm parameter, Model model, HttpServletRequest request) {
        Member member = null;
        log.info("login 요청");
        log.info("userId={}", parameter.getUserId());

        try {
            member = memberService.login(parameter);

        } catch (BizException e) {
            log.info("로그인 에러: " + e.getMessage());
            model.addAttribute("result", false);
            return "/member/login-result";
        }

        HttpSession session = request.getSession();
        session.setAttribute("loginMember", member);
        session.setMaxInactiveInterval(60 * 30);
        model.addAttribute("result", true);

        return "/member/login-result";
    }

    @GetMapping("member/logout")
    public String logout(HttpSession session) {
        log.info("로그아웃 요청");
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("member/detail/")
    public String detail(@RequestParam("id") long id, Model model) {
        log.info("id={}", id);

        MemberDto member = null;

        try {

            member = memberService.detail(id);
        } catch (BizException e) {
            log.info("로그인 에러: " + e.getMessage());
        }

        model.addAttribute("member", member);
        return "/member/detail";
    }



    @PostMapping("/member/addStore")
    public String addStore() {
        return null;
    }
}
