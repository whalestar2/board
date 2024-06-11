package com.bitstudy.board.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

/** 여기서는 gradle 방식으로 코드를 짜볼 것임
 * JPA나 Lombok 같은 디팬던시들을 이용해서 코드를 짤 거임
 *
 * JPA 에너테이션을 이용해서 이 클래스를 엔티티로 바꿔볼거임.
 *
 * @Entity 달기
 * = JPA =
 *      JPA 진영의 ORM(Object Relational Mapping) 기술 표준
 *      Entity 를 분석하고, create나 insert 같은 sql쿼리를 생성해주고, JDBC api를 사용해서 DB 접근까지 해줌.
 *      (객체와 테이블을 맵핑해줌)
 *
 *      Entity = 객체 = DB테이블에 대응하는 하나의 클래스
 *
 */

/*
*  @Entity 등록할 때 반드시 필드 중에 어떤게 PK인지 명시해줘야함
*   @Id: 해당 필드가 PK다 라고 선언하는 에너테이션임.
* */

@Getter
//@Setter
@ToString
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
public class Ex00_1_Article_엔티티로_등록 {
    @Id
    private Long id; //고유번호
    private String title; //제목
    private String content; // 본문
    private String hashtag; //해시태그

    //메타데이터
    private LocalDateTime createdAt; //생성일시
    private String createdBy; //생성자
    private LocalDateTime modifiedAt; //수정일시
    private String modifiedBy; //수정자

//    @Override
//    public String toString() {
//        return "Ex00_1_Article_엔티티로_등록{" +
//                "id=" + id +
//                ", title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                ", hashtag='" + hashtag + '\'' +
//                ", createdAt=" + createdAt +
//                ", createdBy='" + createdBy + '\'' +
//                ", modifiedAt=" + modifiedAt +
//                ", modifiedBy='" + modifiedBy + '\'' +
//                '}';
//    }


    //    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public String getHashtag() {
//        return hashtag;
//    }
//
//    public void setHashtag(String hashtag) {
//        this.hashtag = hashtag;
//    }
//
//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(LocalDateTime createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public String getCreatedBy() {
//        return createdBy;
//    }
//
//    public void setCreatedBy(String createdBy) {
//        this.createdBy = createdBy;
//    }
//
//    public LocalDateTime getModifiedAt() {
//        return modifiedAt;
//    }
//
//    public void setModifiedAt(LocalDateTime modifiedAt) {
//        this.modifiedAt = modifiedAt;
//    }
//
//    public String getModifiedBy() {
//        return modifiedBy;
//    }
//
//    public void setModifiedBy(String modifiedBy) {
//        this.modifiedBy = modifiedBy;
//    }
}
