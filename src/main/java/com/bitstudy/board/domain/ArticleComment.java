package com.bitstudy.board.domain;

import java.time.LocalDateTime;

public class ArticleComment {
    private Long id; //고유번호
    private Article article; // 연관관계 매핑
    /*연관 관계 없이 코드를 짤 거라면
    * private Long FK_id 이런식으로 그냥 하면 됨
    * private Article article;는 Article 과 관계를 맺고 있는 필드라는 걸 객체지향적으로 표현한거임*/
    private String content; // 본문

    //메타데이터
    private LocalDateTime createdAt; //생성일시
    private String createdBy; //생성자
    private LocalDateTime modifiedAt; //수정일시
    private String modifiedBy; //수정자
}
