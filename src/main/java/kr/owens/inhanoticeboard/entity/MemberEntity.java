package kr.owens.inhanoticeboard.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(of = "id")
@ToString
public class MemberEntity {

  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false, unique = true, length = 20)
  private String UId;

  @Column(nullable = false, length = 200)
  private String password;

  @Column(nullable = false, length = 20)
  private String nickName;

  @CreationTimestamp
  private Date regDate;

  @CreationTimestamp
  private Date updateDate;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<MemberRole> roles;
}
