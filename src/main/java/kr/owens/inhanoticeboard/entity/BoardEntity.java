package kr.owens.inhanoticeboard.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
@Builder
@DynamicUpdate
public class BoardEntity {

  @Id
  @Column(unique = true)
  @GeneratedValue
  private Long id;

  @Column(nullable = false , length = 20)
  private String nickName;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String content;

  private String comment;

  @Column(nullable = false)
  private long viewCount;

  @Column(nullable = false)
  private long good;

  @CreationTimestamp
  private Date regDate;
}
