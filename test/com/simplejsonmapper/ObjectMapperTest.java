package com.simplejsonmapper;

import static org.junit.Assert.*;

import org.junit.Test;

public class ObjectMapperTest {

	ObjectMapper objectMapper = new ObjectMapper();

	@Test
	public void testSimpletMapping() throws JsonMappingException{
		String jsonString = "{\"name\": \"potato\", \"amount\": 10, \"price\": 2.5}";
		Product product = objectMapper.map(jsonString, Product.class);
		assertEquals("potato", product.getName());
		assertEquals(10, product.getAmount());
		assertEquals(2.5, product.getPrice(), 0);
	}

	@Test
	public void testAnnotatedMapping() throws JsonMappingException{
		String jsonString = "{\"name\": \"potato\", \"amount\": 10, \"retail_price\": 5.5}";
		Product product = objectMapper.map(jsonString, Product.class);
		assertEquals(5.5, product.getRetailPrice(), 0);
	}
	
	@Test
	public void testArrayMapping() throws JsonMappingException{
		String jsonString = "{\"name\": \"potato\", \"amount\": 10, \"retail_price\": 5.5, \"oldPrices\":[1.1,3.1,2.2]}";
		Product product = objectMapper.map(jsonString, Product.class);
		double prices[] = {1.1,3.1,2.2};
		assertArrayEquals(prices, product.getOldPrices(),0);
	}

}
