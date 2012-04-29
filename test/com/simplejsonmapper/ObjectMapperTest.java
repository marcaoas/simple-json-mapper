package com.simplejsonmapper;

import org.json.JSONException;
import org.json.JSONObject;
import static org.junit.Assert.*;
import org.junit.Test;
import com.simplejsonmapper.ObjectMapper;

public class ObjectMapperTest {

	ObjectMapper objectMapper = new ObjectMapper();

	@Test
	public void testSimpletMapping() throws IllegalArgumentException, InstantiationException, IllegalAccessException, JSONException {
		JSONObject jsonObject = new JSONObject("{\"name\": \"potato\", \"amount\": 10, \"price\": 2.5}");
		Product product = objectMapper.readValue(jsonObject, Product.class);
		assertEquals("potato", product.getName());
		assertEquals(10, product.getAmount());
		assertEquals(2.5, product.getPrice(), 0);
	}

	@Test
	public void testOAnnotatedMapping() throws JSONException, IllegalArgumentException, InstantiationException, IllegalAccessException {
		JSONObject jsonObject = new JSONObject("{\"name\": \"potato\", \"amount\": 10, \"retail_price\": 5.5}");
		Product product = objectMapper.readValue(jsonObject, Product.class);
		assertEquals(5.5, product.getRetailPrice(), 0);
	}

}
