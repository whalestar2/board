package com.bitstudy.board.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@ToString
//@EntityListeners(AuditingEntityListener.class) /* auditing 관련된 거. Ex02_3으로 옮겨 갈 거*/
//@Entity
@Table(indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy"),
         /* AuditingFields.java 사용시 @Index 꺼를 보내야 하는데 그렇게 하지 않음.
            못 보내는 건 아닌데 보낼려면 세팅 해야할 게 너무 많음. 그래서 비효율적임.
         */
})
public class Ex02_2_ArticleComment_공통필드_분리 extends Ex02_3_AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 고유번호

    @Setter
    @ManyToOne(optional = false)
    private Ex02_1_Article_공통필드_분리 article; // 연관관계 매핑.

    @Setter
    @Column(nullable = false, length = 500)
    private String content; // 본문

//auditingFields 로 옮김
//    // 메타데이터
//    @CreatedDate @Column(nullable = false) private LocalDateTime createdAt; // 생성일시
//    @CreatedBy @Column(nullable = false, length = 100) private String createdBy; // 생성자
//    @LastModifiedDate @Column(nullable = false) private LocalDateTime modifiedAt; // 수정일시
//    @LastModifiedBy @Column(nullable = false, length = 100) private String modifiedBy; // 수정자


    protected Ex02_2_ArticleComment_공통필드_분리() {
    }

    private Ex02_2_ArticleComment_공통필드_분리(Ex02_1_Article_공통필드_분리 article, String content) {
        this.content = content;
        this.article = article;
    }

    public static Ex02_2_ArticleComment_공통필드_분리 of(Ex02_1_Article_공통필드_분리 article, String content) {
        return new Ex02_2_ArticleComment_공통필드_분리(article, content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ex02_2_ArticleComment_공통필드_분리 that = (Ex02_2_ArticleComment_공통필드_분리) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}




