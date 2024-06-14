package com.bitstudy.board.domain;
//import 단축키: ctrl + space bar / auto import: 설정에서 자동 가져오기 - 모호하지 않은 import문 즉시 삽입 체크
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/*
        1) 각 도메인 파일들에서 중복됐던 부분들 그대로 가져오기
            - 가져오면서 @MappedSuperClass 붙이기
        2) auditing과 관련된 거 다 가져오기
            - article 에서 auditing 하는 게 다 이동 했으니까 auditing에 관련된 부분들도 다 가져오기(@EntityListeners(AuditingEntityListener.class))
        3) @Getter, @ToString 달기
        4) Aricle 클래스 가서 extends 달아주기(AuditingFields 클래스 상속받게 하기)

 */


@MappedSuperclass /* 부모 클래스는 테이블과 매핑하지 않고,
                    부모클래스를 상속 받는 자식 클래스에게 부모 클래스가 가지는 컬럼만 매핑 정보로 제공하고 싶을 떄 사용
                    위치상 맨 위에 있는 클래스라는 뜻
                    얘를 추상클래스로 만들어서(abstact) 자식에세 extends로 상속하면 자식도 자동으로 매핑될 때 부모 컬럼 제공
                    일을 두 번 안 하게 함. */
@EntityListeners(AuditingEntityListener.class)
@Getter // 각 필드에 접근 해야하니까 필요
@ToString // 편하게 출력하려고
public abstract class Ex02_3_AuditingFields {
    // 메타데이터
    @CreatedDate
    @Column(nullable = false) private LocalDateTime createdAt; // 생성일시
    @CreatedBy
    @Column(nullable = false, length = 100) private String createdBy; // 생성자
    @LastModifiedDate
    @Column(nullable = false) private LocalDateTime modifiedAt; // 수정일시
    @LastModifiedBy
    @Column(nullable = false, length = 100) private String modifiedBy; // 수정자
}
