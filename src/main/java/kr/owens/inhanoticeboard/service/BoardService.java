package kr.owens.inhanoticeboard.service;

import java.util.List;
import java.util.Optional;
import kr.owens.inhanoticeboard.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface BoardService {

  BoardEntity findByNickName(String nickName);

  Optional<BoardEntity> findById(Long Id);

  List<BoardEntity> findAll();

  Page<BoardEntity> getBoardList(Pageable pageable);

  void delete(Long id);

  void save(BoardEntity memberEntity);
}
