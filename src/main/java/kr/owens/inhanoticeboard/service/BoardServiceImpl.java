package kr.owens.inhanoticeboard.service;

import java.util.List;
import java.util.Optional;
import kr.owens.inhanoticeboard.entity.BoardEntity;
import kr.owens.inhanoticeboard.repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {

  private BoardRepository boardRepository;

  @Override
  public BoardEntity findByNickName(String nickName) {
    return boardRepository.findByNickName(nickName);
  }

  @Override
  public List<BoardEntity> findAll() {
    return boardRepository.findAll();
  }

  @Override
  public Optional<BoardEntity> findById(Long Id) {
    return boardRepository.findById(Id);
  }

  @Override
  public void delete(Long id) {
    boardRepository.deleteById(id);
  }

  @Override
  public Page<BoardEntity> getBoardList(Pageable pageable) {
    int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
    pageable = PageRequest.of(page, 10, Sort.by("id").descending());

    return boardRepository.findAll(pageable);
  }

  @Override
  public void save(BoardEntity memberEntity) {
    boardRepository.save(memberEntity);
  }
}
