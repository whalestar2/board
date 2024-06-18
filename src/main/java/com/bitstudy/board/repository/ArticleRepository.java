package com.bitstudy.board.repository;

import com.bitstudy.board.domain.Article;
import com.bitstudy.board.domain.ArticleComment;
import com.bitstudy.board.domain.QArticle;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/*
* findById 메서드는 Spring Data JPA에서 제공하는 기본 메서드입니다. Spring Data JPA의 JpaRepository 인터페이스를 상속받으면, findById를 비롯한 여러 CRUD 메서드를 자동으로 사용할 수 있습니다.
JpaRepository는 CrudRepository와 PagingAndSortingRepository를 확장한 인터페이스로, 기본적인 CRUD (생성, 읽기, 갱신, 삭제) 작업과 페이징 및 정렬 기능을 제공합니다. findById 메서드는 특정 ID를 기준으로 엔티티를 조회하는 데 사용됩니다.
* */


@RepositoryRestResource
public interface ArticleRepository extends
        JpaRepository<Article, Long>  //Article 엔티티와 그 기본 키 타입인 Long을 지정
        , QuerydslPredicateExecutor<Article> /* 얘만 있어도 검색은 됨. 정확한 검색만 가능*/
        , QuerydslBinderCustomizer<QArticle> /* like 검색 */

        /*  QuerydslPredicateExecutor 는 기본적으로 Article 안에 있는 모든 필드에 대한 기본 검색 기능을 추가해줌. (이거만 있어도 검색은 가능함)

            - 간단한 테스트 해보기 위해서 'QuerydslBinderCustomizer<QArticle>' 주석처리 해보고
              QuerydslPredicateExecutor<Article>만 살려서 해보기
              1) 주석처리 하고 BoardApplication 실행하기
              2) HAL 가서 아무 Article 하나 들어가서 ? 뒤에 정보 넣어서 검색 해보기
                    ex) http://localhost:8080/api/articles?hashtag=Yellow
                 이렇게 하면 hashtag 가 정확히 Yellow 인것만 나옴
                 철자가 하나라도 다르면 아무것도 안나옴

         */
{
    /* QuerydslBinderCustomizer<QArticle> 를 사용하려면 customize 메서드 필요함
        customize 오버라이드 하는 방법
        1) Ctrl + O 눌러서 오버라이드 창 열고
        2) 스크롤 내려보면 org.springframework.data.querydsl.binding.QuerydslBinding~ 있음
        3) 그 안에 있는 customize(QuerydslBindings bindings, QArticle root)  선택

        * customize: 세부 규칙 재구성 할 수 있는 메서드
                     원래는 여기가 인터페이스라 구현부를 만들수 없는데
                     java 1.8 이후부터는 default 를 써서 가능함
     */
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
