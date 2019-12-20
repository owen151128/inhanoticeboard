package kr.owens.inhanoticeboard.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LoginController {

  @RequestMapping("/auth/login_request.do")
  @GetMapping
  public String responseGetRequest(Model model) {
    return "login";
  }

  @RequestMapping("/auth/login_illegal.do")
  @GetMapping
  public String responseIllegalRequest(Model model) {
    return "login_illegal";
  }

  @RequestMapping("/auth/logout.do")
  @GetMapping
  public RedirectView responseLogoutRequest(HttpSession session, HttpServletRequest request,
      HttpServletResponse response) {
    Cookie[] cookies = request.getCookies();

    for (Cookie c : cookies) {
      c.setMaxAge(0);
      response.addCookie(c);
    }

    session.invalidate();

    return new RedirectView("/");
  }
}
