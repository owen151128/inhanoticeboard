package kr.owens.inhanoticeboard.controller;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import kr.owens.inhanoticeboard.entity.BoardEntity;
import kr.owens.inhanoticeboard.entity.MemberEntity;
import kr.owens.inhanoticeboard.service.BoardService;
import kr.owens.inhanoticeboard.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@Controller
public class BoardController {

  private final MemberService memberService;
  private final BoardService boardService;

  @RequestMapping("/board/list.do")
  public String responseListRequest(Model model) {
    List<BoardEntity> boardList = boardService.findAll();
    Collections.reverse(boardList);
    model.addAttribute("boardList", boardList);

    return "list";
  }

  @RequestMapping("/board/article.do")
  @GetMapping()
  public String responseArticleRequest(@RequestParam String id, Model model) {
    String userId = SecurityContextHolder.getContext().getAuthentication().getName();
    Optional<BoardEntity> warp = boardService.findById(Long.parseLong(id));
    BoardEntity boardEntity = null;

    if (!warp.isPresent()) {
      throw new IllegalArgumentException("id is illegal.");
    }

    boardEntity = warp.get();

    if (memberService
        .findByNickName(boardEntity.getNickName()).getUId().equals(userId)) {
      model.addAttribute("own", true);
    } else {
      model.addAttribute("own", false);
    }

    model.addAttribute("comment", boardEntity.getComment());
    model.addAttribute("viewCount", boardEntity.getViewCount());
    model.addAttribute("good", boardEntity.getGood());
    model.addAttribute("id", boardEntity.getId());
    model.addAttribute("title", boardEntity.getTitle());
    model.addAttribute("content", boardEntity.getContent());
    model.addAttribute("nickName", boardEntity.getNickName());
    model.addAttribute("regDate", boardEntity.getRegDate());
    long viewCount = boardEntity.getViewCount();
    viewCount++;
    boardEntity.setViewCount(viewCount);
    boardService.save(boardEntity);

    return "article";
  }

  @RequestMapping("/board/update.do")
  public String responseUpdateRequest(@RequestParam String id, Model model) {
    String userId = SecurityContextHolder.getContext().getAuthentication().getName();
    Optional<BoardEntity> warp = boardService.findById(Long.parseLong(id));
    BoardEntity boardEntity = null;

    if (!warp.isPresent()) {
      throw new IllegalArgumentException("id is illegal.");
    }

    boardEntity = warp.get();

    if (memberService
        .findByNickName(boardEntity.getNickName()).getUId().equals(userId)) {
      model.addAttribute("own", true);
    } else {
      model.addAttribute("own", false);
    }

    model.addAttribute("id", boardEntity.getId());
    model.addAttribute("title", boardEntity.getTitle());
    model.addAttribute("content", boardEntity.getContent());
    model.addAttribute("nickName", boardEntity.getNickName());
    model.addAttribute("regDate", boardEntity.getRegDate());

    return "update";
  }

  @RequestMapping("/board/update_proc.do")
  @PostMapping
  public String responseUpdateProcRequest(@RequestParam String id, BoardEntity boardEntity) {
    Optional<BoardEntity> warp = boardService.findById(Long.parseLong(id));
    BoardEntity dbInfo = null;

    if (!warp.isPresent()) {
      throw new IllegalArgumentException("id is illegal.");
    }

    dbInfo = warp.get();

    boardEntity.setGood(dbInfo.getGood());
    boardEntity.setViewCount(dbInfo.getViewCount());
    boardEntity.setNickName(dbInfo.getNickName());
    boardEntity.setRegDate(new Date());
    boardService.save(boardEntity);

    return "proc/update_proc";
  }

  @RequestMapping("/board/delete.do")
  public String responseDeleteRequest(@RequestParam String id, BoardEntity boardEntity) {
    String userId = SecurityContextHolder.getContext().getAuthentication().getName();
    Optional<BoardEntity> warp = boardService.findById(Long.parseLong(id));
    BoardEntity dbInfo = null;

    if (!warp.isPresent()) {
      throw new IllegalArgumentException("id is illegal.");
    }

    dbInfo = warp.get();

    if (!memberService.findByNickName(dbInfo.getNickName()).getUId().equals(userId)) {
      return "proc/illegal";
    }
    boardService.delete(Long.parseLong(id));

    return "proc/delete_proc";
  }

  @RequestMapping("/board/insert.do")
  public String responseInsertRequest() {
    if (SecurityContextHolder.getContext()
        .getAuthentication() instanceof AnonymousAuthenticationToken) {

      return "proc/no_auth";
    }

    return "insert";
  }

  @RequestMapping("/board/insert_proc.do")
  @PostMapping
  public String responseInsertProcRequest(BoardEntity boardEntity) {
    if (SecurityContextHolder.getContext()
        .getAuthentication() instanceof AnonymousAuthenticationToken) {

      return "proc/no_auth";
    }
    String UId = SecurityContextHolder.getContext().getAuthentication().getName();
    MemberEntity memberEntity = memberService.findByUId(UId);

    if (boardEntity.getTitle().equals("") || boardEntity.getContent().equals("")) {
      return "proc/required_is_null";
    }

    boardEntity.setNickName(memberEntity.getNickName());
    boardEntity.setViewCount(0);
    boardEntity.setGood(0);
    boardService.save(boardEntity);

    return "proc/insert_proc";
  }
}
