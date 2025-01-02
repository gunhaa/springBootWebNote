package com.note.web.controller;

import com.note.web.entity.Member;
import com.note.web.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static com.note.web.controller.HttpRequestLogger.ReqLogger;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/members/new")
    public String joinMember(HttpServletRequest request){
        log.info("GET : /members/new");
        ReqLogger(request);
        return "/member/newMember";
    }

    @PostMapping("/member/new")
    public String joinMember(@ModelAttribute Member member, HttpServletRequest request){
        log.info("POST : /members/new");
        ReqLogger(request);
//        System.out.println("member.getEmail() = " + member.getEmail());
//        System.out.println("member.getName() = " + member.getName());
//        System.out.println("member.getPassword() = " + member.getPassword());
        memberRepository.save(member);
        return "home";
    }

    @GetMapping("/members")
    public String memberList(Model model, HttpServletRequest request){
        log.info("GET : /members");
        ReqLogger(request);
        List<Member> memberList = memberRepository.findAll();
//        for (Member member : memberList) {
//            System.out.println("member.getEmail() = " + member.getEmail());
//            System.out.println("member.getName() = " + member.getName());
//        }
        model.addAttribute("memberList", memberList);
        return "/member/memberList";
    }

    @GetMapping("/members/login")
    public String memberLogin(){
        return "/member/memberLogin";
    }

    @PostMapping("/members/login")
    public String memberGetSession(HttpServletRequest request, @ModelAttribute Member member, Model model){

        String loginStatus;

        if(isMember(member)){
            HttpSession session = request.getSession(true);
            loginStatus = "로그인 성공";
            Member findMember = memberRepository.findByEmail(member.getEmail()).get();
            session.setAttribute("member" , findMember);
            model.addAttribute("loginStatus", loginStatus);
            return "home";
        } else{
            loginStatus = "로그인 실패";
            model.addAttribute("loginStatus", loginStatus);
            return "home";
        }

    }

    private boolean isMember(Member submitMember) {
        return memberRepository.findByEmail(submitMember.getEmail())
                .map(member -> member.getPassword().equals(submitMember.getPassword()))
                .orElse(false);
    }

    @GetMapping("/members/needLogin")
    public String needLogin(HttpServletRequest request, Model model){
        HttpSession session = request.getSession(false);
        if(session != null){
            Member member = (Member) session.getAttribute("member");
            model.addAttribute("member", member);
            return "member/sessionTest";
        } else{
            model.addAttribute("loginStatus", "로그인하고 들어갈 수 있습니다.");
            return "home";
        }

    }

}
