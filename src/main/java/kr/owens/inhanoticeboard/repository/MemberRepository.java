package kr.owens.inhanoticeboard.repository;

import kr.owens.inhanoticeboard.entity.MemberEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends CrudRepository<MemberEntity, Long> {

  MemberEntity findByUId(String UId);

  MemberEntity findByNickName(String NickName);
}
