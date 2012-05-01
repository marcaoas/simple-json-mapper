package com.simplejsonmapper;

import static org.junit.Assert.assertEquals;

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
	public void testOAnnotatedMapping() throws JsonMappingException{
		String jsonString = "{\"name\": \"potato\", \"amount\": 10, \"retail_price\": 5.5}";
		Product product = objectMapper.map(jsonString, Product.class);
		assertEquals(5.5, product.getRetailPrice(), 0);
	}
	
	@Test
	public void testCollectionMapping(){
	}

}
