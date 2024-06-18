package com.bitstudy.board.domain.type;
/*
    enum
        상수(final)의 모음
        getter나 setter 롬복 없어도 만들어줌
        대신 getter 이름이 getTitle 이런식으로 하지 않고
        그냥 변수처럼 생긴 이름 사용
 */

// 관리하기가 쉽지 않음. title content... 아티클, 아티클 커멘트,.. 그 안에 변수 많은데 이름 다 일일이 찾아야 하는 수고를 덜기 위해
//서치타입 안에 다 박아둠. 찾기 쉽게
// Ex09_3_searchType. 치면 쓸 수 있는 것들 나옴.
//settitle 안 써도 됨.
//변수명만 넣어주고
// 유효성 검사도 해줄 수 있음.

public enum Ex09_3_searchType {
    TITLE, CONTENT, ID, NICKNAME, HASHTAG
//
//    if(searchkeyword==null){
//
//    }
//
//    switch(tmp){
//        case TITLE -> ??.fingByTitleContaininit(tmp, ) //람다식
//    }

}
