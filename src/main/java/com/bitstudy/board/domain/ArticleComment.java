package com.bitstudy.board.domain;

import com.bitstudy.board.domain.Article;
import com.bitstudy.board.domain.Ex02_3_AuditingFields;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;


@Getter
@ToString(callSuper = true)/*부모 클래스의 toString 메서드 호출을 포함하여 현재 클래스의 toString 메서드를 자동으로 생성해 줌. 주로 상속 구조에서 부모 클래스의 필드 값을 toString 결과에 포함하고자 할 때 사용됨.*/
@Entity
@Table(indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy"),
})
public class ArticleComment extends Ex02_3_AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 고유번호

    @Setter
    @ManyToOne(optional = false)
    private Article article; // 연관관계 매핑.

    /* 추가 - 유저 정보(userId), 양방향 바인딩 */
    @Setter
    @ManyToOne(optional = false)
//    @JoinColumn(name = "userId")
    private UserAccount userAccount;





    @Setter
    @Column(nullable = false, length = 500)
    private String content; // 본문


//    // 메타데이터
//    @CreatedDate @Column(nullable = false) private LocalDateTime createdAt; // 생성일시
//    @CreatedBy @Column(nullable = false, length = 100) private String createdBy; // 생성자
//    @LastModifiedDate @Column(nullable = false) private LocalDateTime modifiedAt; // 수정일시
//    @LastModifiedBy @Column(nullable = false, length = 100) private String modifiedBy; // 수정자


    protected ArticleComment() {
    }

    /* 변경 */
//    private ArticleComment(Article article, String content) {
    private ArticleComment(Article article, UserAccount userAccount, String content) {
        this.content = content;
        this.userAccount = userAccount;
        this.article = article;
    }

    /* 변경 */
//    public static ArticleComment of(Article article, String content) {
    public static ArticleComment of(Article article, UserAccount userAccount, String content) {
        return new ArticleComment(article, userAccount, content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleComment that = (ArticleComment) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
