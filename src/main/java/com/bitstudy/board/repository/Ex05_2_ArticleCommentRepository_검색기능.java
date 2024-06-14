package com.bitstudy.board.repository;

import com.bitstudy.board.domain.Article;
import com.bitstudy.board.domain.ArticleComment;
import com.bitstudy.board.domain.QArticle;
import com.bitstudy.board.domain.QArticleComment;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// 클래스는 다중상속 못 받으므로 인터페이스로 만듦.
@RepositoryRestResource/* yaml 파일에서 detection-strategy: annotated 대응 하는 어노테이션 */
public interface Ex05_2_ArticleCommentRepository_검색기능 extends
        JpaRepository<ArticleComment, Long>
        , QuerydslPredicateExecutor<ArticleComment> /* 얘만 있어도 검색됨 (정확한 검색만 가능)*/
        , QuerydslBinderCustomizer<QArticleComment> /*  like 검색 */
{

    @Override
    default void customize(QuerydslBindings bindings, QArticleComment root){
        bindings.excludeUnlistedProperties(true);

        bindings.including( root.content,  root.createdAt, root.createdBy); //검색에 쓰일 컬럼들

        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);
        // 날짜니까 DateTimeExpression 사용. eq는 equals 뜻
        // ctrl + space 해서 import 해야함
        // 날짜 필드는 완벽히 같은 것만 검색되도록 할거임
        // 근데 이렇게 하면 시분초를 다 0으로 인식하기 때문에 조심해야함
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
    }

        /* 다 하면
        1) 빌드 (Ctrl + F9)
        2) Hal 가서 확인해보기
            ex) http://localhost:8080/api/articles?hashtag=Yell
     */
}