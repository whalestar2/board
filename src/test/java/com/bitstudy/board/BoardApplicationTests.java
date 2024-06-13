package com.bitstudy.board;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BoardApplicationTests {
	
	/*TDD
	원래 컨트롤러 서비스 다오 마이바티스 디비까지 모든 경로를 돌려야하는데
	최대한 간결하게 슬라이스 테스트 할 수 있게 환경을 만들어주는것. 
	임의의 정보 넣어주고 Dao 과정 테스트. 
		
	 */
	@Test
	void contextLoads() {
//		경로에 한글 있으면 터짐.
	}

}
