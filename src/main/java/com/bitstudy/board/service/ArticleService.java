package com.bitstudy.board.service;

import com.bitstudy.board.domain.type.Ex09_3_searchType;
import com.bitstudy.board.dto.ArticleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor // 필수 필드에 대한 생성자를 자동으로 만들어주는 롬복
@Transactional // 하나라도 안 되면 다시 롤백 되는
public class ArticleService {

    // 1. 검색
//    @Transactional(readOnly = true) // 변경하는 게 아니고 읽기만 할거니까 reanOnly 걸기
//    public List<ArticleDto> searchArticles(Ex09_3_searchType title, String keyword) {
//        return List.of();
//    }

    // 1. 검색 & 3.페이지네이션
    @Transactional(readOnly = true) //// 변경하는 게 아니고 읽기만 할거니까 reanOnly 걸기
    public Page<ArticleDto> searchArticles(Ex09_3_searchType title, String keyword) {
        return Page.empty();
    }

    // 2. 각 게시글 페이지로 이동
    public ArticleDto searchArticle(Long l) {
        return null; // 아직 service로직이 구현되어 있지 않았으니까 일단 테스트 실패하게 null 넣은 거임
    }
}
