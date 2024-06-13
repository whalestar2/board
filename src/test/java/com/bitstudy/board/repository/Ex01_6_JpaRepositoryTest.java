package com.bitstudy.board.repository;

import com.bitstudy.board.repository.Ex01_4_ArticleRepository;
import com.bitstudy.board.repository.Ex01_5_ArticleCommentRepository;
import com.bitstudy.board.config.Ex01_3_JpaConfig;
import com.bitstudy.board.domain.Ex01_1_Article_엔티티로_등록;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest // 슬라이스 테스트를 할수 있게 해주는 어노테이션.
// 슬라이스 테스트를 할때 우리가 수동으로 만든 JpaConfig 파일을 읽어오지는 않기 때문에
// 요 아래 @Import 어노테이션을 이용해서 해당 파일 정보를 읽어올수 있게 해야함
@Import(Ex01_3_JpaConfig.class) // 테스트 파일에서 JPA Auditing 구성 정보 알아보게 하기
class Ex01_6_JpaRepositoryTest {

//    @Autowired Ex01_4_ArticleRepository articleRepository;
//    @Autowired Ex01_5_ArticleCommentRepository articleCommentRepository;

    // 생성자 주입
    Ex01_4_ArticleRepository articleRepository;
    Ex01_5_ArticleCommentRepository articleCommentRepository;

    public Ex01_6_JpaRepositoryTest(@Autowired Ex01_4_ArticleRepository articleRepository, @Autowired Ex01_5_ArticleCommentRepository articleCommentRepository) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @Test
    void selectTest() {
        List<Ex01_1_Article_엔티티로_등록> articles = articleRepository.findAll();
        assertThat(articles).isNotNull().hasSize(1000);
    }

//    데이터베이스에 테이블이 안 만들어지면 gradle- board - tasks - build - clean 해주고
//    other - compilejava 하고 다시 돌려보기 (compilejava): 빌드까진 아니더라도  쭉 훑어서 필요한 거 다운해줌.


    //mockaroo: 가짜 데이터 불러오기 (mock: 가짜)-> sql 데이터 다운로드해서 data.sql에 복붙.

}

















