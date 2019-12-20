package kr.owens.inhanoticeboard.service;

import kr.owens.inhanoticeboard.entity.MemberEntity;


public interface MemberService {

  MemberEntity findByUId(String UId);

  MemberEntity findByNickName(String NickName);

  void save(MemberEntity memberEntity);
}