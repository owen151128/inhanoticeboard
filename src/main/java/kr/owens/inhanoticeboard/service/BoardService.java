package kr.owens.inhanoticeboard.service;

import java.util.List;
import java.util.Optional;
import kr.owens.inhanoticeboard.entity.BoardEntity;

public interface BoardService {

  BoardEntity findByNickName(String nickName);

  Optional<BoardEntity> findById(Long Id);

  List<BoardEntity> findAll();

  void delete(Long id);

  void save(BoardEntity memberEntity);
}
