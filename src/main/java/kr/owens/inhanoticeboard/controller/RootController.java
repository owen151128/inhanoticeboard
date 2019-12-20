package kr.owens.inhanoticeboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/")
public class RootController {

  @RequestMapping(method = RequestMethod.GET)
  public RedirectView responseGetRequest(Model model) {
    return new RedirectView("/index.do");
  }
}
