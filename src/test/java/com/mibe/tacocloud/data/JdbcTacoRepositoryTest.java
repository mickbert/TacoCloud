package com.mibe.tacocloud.data;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mibe.tacocloud.Taco;


@ExtendWith(SpringExtension.class)
@JdbcTest
@ComponentScan(basePackages = "com.mibe.tacocloud.data")
//@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class JdbcTacoRepositoryTest {

	@Autowired
	TacoRepository repositoy;

	@DisplayName("should add")
	@Test
	public void testAdd() {
		Taco taco = new Taco();
		taco.setName("yo");
		taco.setIngredients(Arrays.asList(new String[] {"FLTO", "COTO"}));
		repositoy.save(taco);
		assertEquals(taco.getId(), 1L);
		assertEquals(taco.getName(), "yo");
		assertEquals(2, taco.getIngredients().size());
	}

	
}
