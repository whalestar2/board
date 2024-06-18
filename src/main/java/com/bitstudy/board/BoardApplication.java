package com.bitstudy.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan /* config < ThymeleafConfig 파일 만들면 해당 config 파일이 스캔될 수 있도록 이 어노테이션을 달아줘야 함 */
public class BoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardApplication.class, args);
	}
	//이 어플리케이션 전체 구동시켜라(프로젝트 board 전체를 어플리케이션이라고 함)
	//무조건 도는 거. ( 테스트 할 때도 이게 돌고 난 후 테스트 함) 
	// drop table, create table 하고 데이터 넣고 다 함
	// 스프링의 HomeController 와 같은 거.

//사용자한테 정보를 받아서 실행
}
