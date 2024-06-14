package com.bitstudy.board.repository;

import com.bitstudy.board.domain.Ex01_2_ArticleComment_엔티티로_등록;
import com.bitstudy.board.config.Ex01_3_JpaConfig;
import com.bitstudy.board.domain.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest // 슬라이스 테스트를 할수 있게 해주는 어노테이션.
            // @DataJpaTest가 붙으면 테스트 후 롤백됨.
// 슬라이스 테스트를 할때 우리가 수동으로 만든 JpaConfig 파일을 읽어오지는 않기 때문에
// 요 아래 @Import 어노테이션을 이용해서 해당 파일 정보를 읽어올수 있게 해야함
@Import(Ex01_3_JpaConfig.class) // 테스트 파일에서 JPA Auditing 구성 정보 알아보게 하기
class Ex01_6_JpaRepositoryTest {

//    @Autowired Ex01_4_ArticleRepository articleRepository;
//    @Autowired Ex01_5_ArticleCommentRepository articleCommentRepository;

    // 생성자 주입
    ArticleRepository articleRepository;
    ArticleCommentRepository articleCommentRepository;

    public Ex01_6_JpaRepositoryTest(@Autowired ArticleRepository articleRepository, @Autowired ArticleCommentRepository articleCommentRepository) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    /* 트랜잭션시 사용하는 메서드
            사용법: repository 이름.메서드(Sort.by(정렬기준, "기준 컬럼명"))
                1) findAll() - 모든 컬럼을 조회. 페이지 가능
                                당연히 select 작업을 하지만, 잠깐 사이에 해당 테이블에 어떤 변화가 있는지 알 수 없다.
                                그래서 항상 select 하기 전에 update 를 하고 동작 시켜야 함
                2) findById() - 한 번에 한건에 대한 데이터 조회
                                primary key로 조회
                3) save() - 저장(insert, update)
                4) count() - 레코드 개수 뽑을 떄 사용
                5) delete() - 삭제(delete)
         */

    @DisplayName("select테스트") //콘솔창에 나타나는 이름.
    @Test
    void selectTest() {

        /* given_when_then 패턴 만들고 테스트 하기( 단축코드 만들기)
            gwt 단축키 생성 방법:
            세팅(ctrl + alt + S) 에서 라이브 템플릿(live templet) 검색
            + 버튼 > template group으로 그룹 생성하고(이름은 맘대로)
            + 버튼 > live Template로 단축키 설정하기
            하단에 Abbreviation 이름 넣기 (ex. gw,gwt)
            templat text 영역에 자동완성될 문구 넣기 (ex. //given //when //then)
            맨 아래 'Applicable in Java~' 부분의 define또는 change 눌러서 Java 영역에서
            comment, consumer function, Expression, Statement 체크하기
            그럼 앞으로 gw나 gwt라고 치면 자동완성 됨.

         */


        //given

        //when
        List<Article> articles = articleRepository.findAll();
        //then
        assertThat(articles).isNotNull().hasSize(1000);

    }


    @DisplayName("select테스트 내가 직접 하기")
    @Test
    void selectTest2(){
        List<Article> article = articleRepository.findAll();
        assertThat(article).isNotNull().hasSize(1000);
    }

// 설정 - 빌드 도구 - gradle 에서 빌드 및 실행, 테스트 및 실행을 상황에 따라 gradel, intellij IDEA 로 바꿔서 실행시킴. 테스트 오류나면 확인하기.


//    데이터베이스에 테이블이 안 만들어지면 gradle- board - tasks - build - clean 해주고
//    other - compilejava 하고 다시 돌려보기 (compilejava): 빌드까진 아니더라도  쭉 훑어서 필요한 거 다운해줌.


    //mockaroo: 가짜 데이터 불러오기 (mock: 가짜)-> sql 데이터 다운로드해서 data.sql에 복붙.

    //drop table ex01_2_article_comment_엔티티로_등록 cascade;
//    cascade: 자식이 있는지 확인하고 있으면 안 지움. 



    /* insert 테스트 */
    @DisplayName("insert 테스트")
    @Test
    void insertTest(){







        //given
        //기존 카운트 구하기
        long prevCount =  articleRepository.count();

        //when
        // 삽입 - DB에 삽입 순서: DTO에 title, content, hashtag 담아서 넘기기

        Article article = Article.of("제목1", "내용1", "Red");

        Article saveArticle =  articleRepository.save(article);

        System.out.println("save()하고 리턴받는거: " + saveArticle);


        //then
        // 현재 카운트가 기존 카운트 +1 이면 테스트 통과
        assertThat(articleRepository.count()).isEqualTo(prevCount + 1);

    }

    /* insert 테스트 - 내가 직접 하기 */
    @DisplayName("insert 테스트 - 내가 직접 하기")
    @Test
    void insertTest2(){
//    int가 아니라 long
        long prevCount = articleRepository.count();

        Article article2 = Article.of("제목","내용", "white");

        Article insertArticle = articleRepository.save(article2);

        long currentCount = articleRepository.count();
        //isEqualsTo(값): 값과 같은가.
        assertThat(currentCount).isEqualTo(prevCount + 1);
    }


    /* update 테스트 */
    @DisplayName("update 테스트")
    @Test
    void updateTest(){
        /* 기존 데이터 있어야 하고, 그걸 수정했을 때 확인 해야함
            1) 기존의 영속성 컨텍스트(pc, personality context, 무한에 가깝게 저장해주는 거) 1개 엔티티(객체) 가져오기
            2) 업데이트 - (해시태그 없데이트 해보기)

         */
    //given
    /* 순서 - 1) 기존의 영속성 컨텍스트 1개 엔티티(객체) 가져오기
          1. 기존의 영속성 컨텍스트로부터 하나 엔티티를 가져올건데 -> articleRepository.findById()
          2. 글번호 1번은 보통 무조건 있으니까 -> articleRepository.findById(1L)
          3. 없으면 throw 시켜서 일단 현재 테스트는 끝나게 하기 -> .orElseThrow();

     */
        Article article = articleRepository.findById(1L).orElseThrow();

    /* 순서 - 2) 업데이트 - (해시태그 없데이트 해보기)
          1.

     */
        String updateHashtag = "Blue"; //변수에 업데이트할 문자열 넣고
        article.setHashtag(updateHashtag); //엔티티에 있는 setter를 이용해서 변수를 업데이트 하기


    //when - 테스트 해야하는 내용

        // Article savedArticle = articleRepository.save(article); //PK가 null이면 insert, 있으면 update 함.
        Article savedArticle = articleRepository.saveAndFlush(article);
/*
    영속성 컨텍스트로부터 가져온 데이터를 그냥 save만 하고 아무것도 안하고 끝내버리면 어차피 롤백됨.
    테스트를 돌리면 Run탭에 마지막 메세지는 select 구문이 나온다.
    그래서 save 하고 flush해줘서 해당 필요한 구문까지만 나오게 하기

    * saveAndFlush: 바로 DB에 적용하는 방식

    Flush란 push같은 거
    - flush 동작 과정
        1. 변경점 감지
        2. 수정된 Entity(article)를 '지연 sql 저장소'에 등록( 임시로 가서 대기 중)
        3. 쓰기 지연 sql 저장소의 쿼리를 DB에 전송 ( 등록, 수정, 삭제 쿼리)

        영속성 컨텍스트?
        사용자가 정보 보내면 어디로 갈지(지연저장소/DB) 결정하는 곳으로 보내짐
        1차캐시: 어디로 가는지 경로를 저장함. 계속 남음.
 */


    //then

        // savedArticle이 "hashtag" 필드를 가지고 있는데, 그 필드에 updateHashtag 값이 있는지 확인해라.
        assertThat(savedArticle).hasFieldOrPropertyWithValue("hashtag", updateHashtag);



    }

    /* update 테스트 - 내가 직접 하기 */
    @DisplayName("update 테스트 - 내가 직접 하기")
    @Test
    void updateTest2(){
        Article article = articleRepository.findById(1L).orElseThrow();
        String updateHashtag = "black";
        article.setHashtag(updateHashtag);

        Article updatedArticle =  articleRepository.saveAndFlush(article);

        assertThat(updatedArticle).hasFieldOrPropertyWithValue("hashtag", updateHashtag);
    }


    /* delete 테스트 */
    @DisplayName("delete 테스트")
    @Test
    void deleteTest(){
        /* 기존에 데이터 있어야 되고
        값을 하나 꺼내서 지우기

        1) 기존의 영속성 컨테스트로부터 엔티티 꺼내오기
        2) 지우면 DB 갯수 하나 줄어드는 거니까 미리 엔티티 갯수(count) 구하기
        3) 하나 삭제
        4) (2번에서 구한 갯수 - 1) 과 현재 새로 구한 count랑 비교해서 같으면 통과
         */

        //given
        /* 1) 기본의 영속성 컨테스트로부터 엔티티 꺼내오기*/
        Article article = articleRepository.findById(1L).orElseThrow();

        /* 2)지우면 DB 개수 하나 줄어드는 거니까 미리 엔티티 개수(count) 구하기
            게시글(articleRepository) 뿐만 아니라 연관된 댓글(articleCommentRepository) 까지 삭제할 거라서 두 개 갯수를 다 뽑아야 함.
        */
            long prevArticleCount = articleRepository.count();
            long prevArticleCommentCount = articleCommentRepository.count();

            int deleteCommentSize = article.getArticleComment().size();
        /* 양방향 바인딩 해놔서 ArticleComment 에 담겨 있는거 를 가져올 수 있음.
          Ex01_1_Article_엔티티로 등록에 private final Set<Ex01_2_ArticleComment_엔티티로_등록> articleComment = new LinkedHashSet<>();
         */


        //when
        articleRepository.delete(article);

        //then
        //현재 게시글 갯수(articleRepository.count()) 가 아까 구한 prevArticleCount 보다 1적으면 테스트 통과
        assertThat(articleRepository.count()).isEqualTo(prevArticleCount - 1);

        // 현재 게시글의 대한 댓글 갯수
        assertThat(articleCommentRepository.count()).isEqualTo(prevArticleCommentCount - deleteCommentSize);
    }


    /* delete 테스트 - 내가 직접 하기 */
    @DisplayName("delete 테스트 - 내가 직접 하기")
    @Test
    void deleteTest2(){

        long prevArticleCount = articleRepository.count();
        long prevArticleCommentCount = articleCommentRepository.count();

        Article article = articleRepository.findById(1L).orElseThrow();

//        데이터 하나 뽑아낸 거랑 연동된 articleComment의 사이즈 구하기
        int articleCommentSize = article.getArticleComment().size();


        articleRepository.delete(article);

        long currentArticleCount = articleRepository.count();
        long currentArticleCommentCount = articleCommentRepository.count();


        assertThat(currentArticleCount).isEqualTo(prevArticleCount - 1);

        // 현재 articlecomment 테이블 총 갯수 = 삭제 전 댓글 총 갯수 - 삭제한 게시글의 댓글 수
        assertThat(currentArticleCommentCount).isEqualTo(prevArticleCommentCount - articleCommentSize);
    }

}

















