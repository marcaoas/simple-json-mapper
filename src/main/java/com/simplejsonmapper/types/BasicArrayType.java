package com.simplejsonmapper.types;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

import org.json.JSONArray;

import com.simplejsonmapper.JsonMappingException;

public class BasicArrayType extends Type {

	private static BasicArrayType INSTANCE;

	private BasicArrayType() {

	}

	@Override
	public void set(Field field, Object object, Object value) throws JsonMappingException {
		try {
			JSONArray basicJsonArray = (JSONArray) value;
			Object array =  Array.newInstance(field.getType().getComponentType(), basicJsonArray.length());
			for (int i = 0; i < basicJsonArray.length(); i++) {
				Array.set(array, i, basicJsonArray.get(i));
			}
			field.set(object, array);
		} catch (Exception e) {
			throw new JsonMappingException(e);
		}

	}

	public static Type getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new BasicArrayType();
		}
		return INSTANCE;
	}

	public static boolean isBasicArray(Field field) {
		boolean isBasicArray = false;
		if (!field.getType().isArray()) {
			isBasicArray = false;
		} else {
			isBasicArray = BasicType.isBasicType(field.getType().getComponentType());
		}
		return isBasicArray;
	}

}
