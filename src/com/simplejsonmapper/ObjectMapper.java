
package com.simplejsonmapper;
import java.lang.reflect.Field;

import org.json.JSONException;
import org.json.JSONObject;

public class ObjectMapper {
	
	public <T> T readValue(String jsonString, Class<T> clazz) throws IllegalArgumentException, InstantiationException, IllegalAccessException, JSONException{
		JSONObject jsonObject = new JSONObject();
		return readValue(jsonObject, clazz);
	}
	
	public <T> T readValue(JSONObject jsonObject, Class<T> clazz) throws InstantiationException, IllegalAccessException, IllegalArgumentException, JSONException {
		T t = clazz.newInstance();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			String fieldName = getFieldName(field);
			if (jsonObject.has(fieldName)) {
				field.setAccessible(true);
				field.set(t, jsonObject.get(fieldName));
			}
		}
		return t;
	}

	private String getFieldName(Field field) {
		JsonField annotation = field.getAnnotation(JsonField.class);
		return annotation != null ? annotation.value() : field.getName();
	}

}
