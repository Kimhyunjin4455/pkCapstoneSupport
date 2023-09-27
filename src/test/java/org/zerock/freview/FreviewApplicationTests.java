package org.zerock.freview;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FreviewApplicationTests {

	@Autowired MemoRepository memoRepository;

	@Test
	public void testClass(){
		System.out.println(memoRepository.getClass().getName());
	}

}
