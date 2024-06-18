package com.bitstudy.board.service;

import com.bitstudy.board.domain.type.Ex09_3_searchType;
import com.bitstudy.board.dto.ArticleDto;
import com.bitstudy.board.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;
/**
    서비스 비지니스 로직은 스프링 부느의 슬라이스 테스트 기능 사용하지 않고 작성해볼 거임.
    이유: 어플리케이션 컨텍스트가 뜨는데 걸리는 시간을 없애기 위해(가볍게 만들기)

        가능한 가볍게 만들기 위해 불필요한 스프링부트 어플리케이션을 띄우는 걸 생략하는 대신
        디펜던시가 추가 되어야 하는게 있으면 Mocking을 하는 방식으로 해볼거임.

        그걸 가능하게 할 수 있게 해주는 라이브러리 (mokito)가 있음.
 @ExtendWith(MockitoExtension.class)  쓰면 됨

 junit 안 쓰고 assertJ



 */

@ExtendWith(MockitoExtension.class)
class Ex09_2_ArticleServiceTest {
    /* Mock을 주입하는거에다 @InjectMocks 달아줘야 함. (그 외의 것들은 @Mock을 달아준다) 외워야함.
        Mock을 주입하는 대상에는 @InjectMocks를 줘야함
     */
    @InjectMocks
    private ArticleService sut; // sut - system under test
    // 실무에서 테스트 짤 떄 '이건 테스트 대상이다'라는 뜻

    @Mock
    private ArticleRepository articleRepository; // 의존하는 걸 가져와야 함(테스트 중간에 mocking 할 때 필요)

    /* 테스트 할 항목들
        1. 검색
        2. 각 게시글 페이지로 이동
        3. 페이지네이션
        4. 홈버튼 클릭하면 게시판 페이지로 이동(컨트롤러에서 하기)
     */

    /* 1. 검색 */
    @DisplayName("검색어 없이 게시글 검색하면, 게시글 페이지 반환")
    @Test
    void articleAll() {
        //given


        //when
        /* 제목 , 본문, id, 닉네임, 해시태그 중에서 제목으로만 검색되게 해보기 */
        //List<ArticleDto> articles = sut.searchArticles(Ex09_3_searchType.TITLE, "검색어");

        /* 3. 페이지네이션 */
        Page<ArticleDto> articles = sut.searchArticles(Ex09_3_searchType.TITLE, "검색어");
        /*  List: List는 컬렉션, 요소들의 순서가 있고 중복 허용한다.
                일반적으로 모든 요소들을 메모리에 저장.
                (만약 데이터가 1000개 있으면 10개만 필요해도 한번에 1000개를 다 가져와서 메모리에 저장)
                작은 데이터셋에 대한 작업시 유용

             Page: 대향의 데이터를 페이징해서 처리할 때 사용
                    데이터를 작은 페이지 단위로 나눠서 읽고 처리함
                    (한번에 모든 데이터를 로드하지 않고 필요한 만큼의 데이터만 메모리에 로드함.
                    그래서 필요없는 메모리 낭비가 없어서 유용함)
                    큰 데이터셋에 대한 작업시 유용
                    Page는 일반적으로 페이지번호, 페이지크기, 총 개수 등의 정보를 가지고 있음

         */


        //then
        assertThat(articles).isNotNull();

    }

    /* 2. 각 게시글 페이지로 이동 */
    //@Disabled
    @DisplayName("검색어 없이 게시글 검색하면, 게시글 페이지 반환")
    @Test
    void selectOne(){
        //given
        //ArticleDto articles = sut.searchArticles(1L);
        //when

        //then
    }



}