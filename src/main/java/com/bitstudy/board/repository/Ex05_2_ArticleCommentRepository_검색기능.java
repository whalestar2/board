package com.bitstudy.board.repository;

import com.bitstudy.board.domain.ArticleComment;
import com.bitstudy.board.domain.QArticleComment;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource
public interface Ex05_2_ArticleCommentRepository_검색기능 extends JpaRepository<ArticleComment, Long>
        , QuerydslPredicateExecutor<ArticleComment>
        //, QuerydslBinderCustomizer<QArticleComment> /* like 검색 */
{



}
