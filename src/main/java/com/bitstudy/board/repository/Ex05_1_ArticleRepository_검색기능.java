package com.bitstudy.board.repository;

import com.bitstudy.board.domain.Article;
import com.bitstudy.board.domain.QArticle;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface Ex05_1_ArticleRepository_검색기능 extends
        JpaRepository<Article, Long>
        , QuerydslPredicateExecutor<Article> /* 얘만 있어도 검색은 됨. 정확한 검색만 가능*/
        , QuerydslBinderCustomizer<QArticle> /* like 검색 */

        /*  QuerydslPredicateExecutor 는 기본적으로 Article 안에 있는 모든 필드에 대한 기본 검색 기능을 추가해줌. (이거만 있어도 검색은 가능함)


         */{


    @Override
    default void customize(QuerydslBindings bindings, QArticle root) {


        bindings.excludeUnlistedProperties(true); // 리스트를 만들건데 그 리스스에 있는 컬럼만 검색되게 해라
        // ex) localhost:8080/api?키=값

        /* 원하는 필드를 추가하는 부분 */
        bindings.including(root.title, root.content, root.hashtag, root.createdAt, root.createdBy);
//        bindings.bind(root.title).first(StringExpression::likeIgnoreCase); // 쿼리상 "like '${문자열}'" 로 들어감

        bindings.bind(root.title).first(StringExpression::containsIgnoreCase); // "like '%문자열$'
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq); // 날짜니까 DateTimeExpression 사용. eq는 equals 뜻
        // 날짜 필드는 완벽히 같은 것만 검색되도록 할거임
        // 근데 이렇게 하면 시분초를 다 0으로 인식하기 때문에 조심해야함
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
    }

}
