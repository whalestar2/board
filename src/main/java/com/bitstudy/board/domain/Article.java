package com.bitstudy.board.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString
@Entity // @Entity 달린 거 파일 이름으로 테이블 만들어짐. data.sql 가져와서
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy"),
        /* AuditingFields.java 사용시 @Index 꺼를 보내야 하는데 그렇게 하지 않음.
            못 보내는 건 아닌데 보낼려면 세팅 해야할 게 너무 많음. 그래서 비효율적임.
         */

})
public class Article extends Ex02_3_AuditingFields {
    @Id //@Entitiy 붙이면 반드시 있어야 함
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 고유번호


    @Setter
    @Column(nullable = false)
    private String title;

    @Setter
    @Column(nullable = false, length = 10000)
    private String content;

    @Setter
    private String hashtag;

    @OrderBy("id")
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    @ToString.Exclude
    private final Set<ArticleComment> articleComment = new LinkedHashSet<>();

    protected Article() {
    }

    private Article(String title, String content, String hashtag) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }


    public static Article of(String title, String content, String hashtag) {
        return new Article(title, content, hashtag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return id!=null && id.equals(article.id);
    }

    @Override
    public int hashCode() { // 동일성 비교
        return Objects.hashCode(id);
    }

}
