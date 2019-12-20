package kr.owens.inhanoticeboard.service;

import java.util.NoSuchElementException;
import java.util.Optional;
import kr.owens.inhanoticeboard.entity.SecurityMember;
import kr.owens.inhanoticeboard.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private MemberRepository memberRepository;

  @Override
  public UserDetails loadUserByUsername(String UId) throws UsernameNotFoundException {
    SecurityMember userInfo;
    try {
      userInfo = Optional.ofNullable(memberRepository.findByUId(UId))
          .map(SecurityMember::new).get();
    } catch (NoSuchElementException e) {
      throw new UsernameNotFoundException(UId);
    }

    return userInfo;
  }
}
