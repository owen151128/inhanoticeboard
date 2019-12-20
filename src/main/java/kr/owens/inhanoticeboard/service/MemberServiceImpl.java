package kr.owens.inhanoticeboard.service;

import kr.owens.inhanoticeboard.entity.MemberEntity;
import kr.owens.inhanoticeboard.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {

  private MemberRepository memberRepository;

  @Override
  public MemberEntity findByUId(String UId) {
    return memberRepository.findByUId(UId);
  }

  @Override
  public MemberEntity findByNickName(String NickName) {
    return memberRepository.findByNickName(NickName);
  }

  @Override
  public void save(MemberEntity memberEntity) {
    memberRepository.save(memberEntity);
  }
}

