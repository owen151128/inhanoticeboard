package kr.owens.inhanoticeboard.controller;

import java.util.stream.IntStream;
import kr.owens.inhanoticeboard.repository.MemberRepository;
import kr.owens.inhanoticeboard.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@AllArgsConstructor
@Controller
@RequestMapping("/index.do")
public class IndexController {

  private final MemberService memberService;

  @RequestMapping(method = RequestMethod.GET)
  public String responseGetRequest(Model model) {
    if (SecurityContextHolder.getContext()
        .getAuthentication() instanceof AnonymousAuthenticationToken) {
      model.addAttribute("userNickName", "로그인을 해 주세요.");
    } else {
      String UId = SecurityContextHolder.getContext().getAuthentication().getName();
      String text = memberService.findByUId(UId).getNickName() + "님 환영합니다.";
      model.addAttribute("userNickName", text);
    }

    return "index";
  }

}
