package kr.owens.inhanoticeboard.dto;

import java.util.Date;
import java.util.List;
import kr.owens.inhanoticeboard.entity.MemberEntity;
import kr.owens.inhanoticeboard.entity.MemberRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class MemberDTO {

  private Long id;
  private String UId;
  private String password;
  private String nickName;
  private Date regDate;
  private Date updateDate;
  private List<MemberRole> roles;

  public MemberEntity toEntity() {

    return MemberEntity.builder()
        .id(id)
        .UId(UId)
        .password(password)
        .nickName(nickName)
        .regDate(regDate)
        .updateDate(updateDate)
        .roles(roles)
        .build();
  }
}
