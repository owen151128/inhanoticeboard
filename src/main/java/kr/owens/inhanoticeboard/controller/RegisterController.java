package kr.owens.inhanoticeboard.controller;

import java.util.Collections;
import kr.owens.inhanoticeboard.entity.MemberEntity;
import kr.owens.inhanoticeboard.entity.MemberRole;
import kr.owens.inhanoticeboard.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
public class RegisterController {

  private final MemberService memberService;

  @RequestMapping("/auth/register.do")
  @GetMapping
  public String responseGetRequest(Model model) {
    return "register";
  }

  @RequestMapping("/auth/register_proc.do")
  @PostMapping
  public String createMember(MemberEntity member) {
    MemberRole role = new MemberRole();
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    member.setPassword(passwordEncoder.encode(member.getPassword()));
    role.setRoleName("BASIC");
    member.setRoles(Collections.singletonList(role));
    memberService.save(member);

    return "proc/register_proc";
  }
}
