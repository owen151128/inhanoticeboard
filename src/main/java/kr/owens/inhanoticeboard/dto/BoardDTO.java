package kr.owens.inhanoticeboard.dto;

import java.util.Date;
import java.util.List;
import java.util.Map;
import kr.owens.inhanoticeboard.entity.BoardEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDTO {

  private Long id;
  private String nickName;
  private String title;
  private String content;
  private String comment;
  private long viewCount;
  private long good;
  private Date regDate;

  public BoardEntity toEntity() {
    return BoardEntity.builder()
        .id(id)
        .nickName(nickName)
        .title(title)
        .content(content)
        .comment(comment)
        .viewCount(viewCount)
        .good(good)
        .regDate(regDate)
        .build();
  }
}
