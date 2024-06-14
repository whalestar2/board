package com.bitstudy.board.repository;

import com.bitstudy.board.domain.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<ArticleComment, Long> {
}
