package kr.owens.inhanoticeboard.repository;

import java.util.List;
import java.util.Optional;
import kr.owens.inhanoticeboard.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

  BoardEntity findByNickName(String nickName);

  Optional<BoardEntity> findById(Long Id);

  void deleteById(Long Id);

  List<BoardEntity> findAll();
}
