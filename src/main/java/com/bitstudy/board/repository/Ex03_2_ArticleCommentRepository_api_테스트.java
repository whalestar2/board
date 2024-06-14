package com.bitstudy.board.repository;

import com.bitstudy.board.domain.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource /*yaml 파일에서 detection-strategy: annotated 대응 하는 어노테이션*/
public interface Ex03_2_ArticleCommentRepository_api_테스트 extends JpaRepository<ArticleComment, Long> {
    //JpaRepository<T,D> 인터페이스를 상속 받는 거임

}
