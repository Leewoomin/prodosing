package com.min.prodosing;

import com.min.prodosing.entity.BoardEntity;
import com.min.prodosing.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@DataJpaTest
class ProdosingApplicationTests {

	@Autowired
	private BoardRepository boardRepository;


	@Test
	void test() {
		List<BoardEntity> boardEntity = boardRepository.findAll();

	}

}
