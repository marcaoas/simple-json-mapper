package com.simplejsonmapper.types;

import java.lang.reflect.Field;

import com.simplejsonmapper.JsonMappingException;

public class ObjectArrayType extends Type {

	private static ObjectArrayType INSTANCE;

	private ObjectArrayType() {

	}
	
	@Override
	public void set(Field f, Object object, Object value) throws JsonMappingException {
	}
	
	public static Type getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ObjectArrayType();
		}
		return INSTANCE;
	}

	public static boolean isObjectArray(Field field) {
		boolean isObjectArray = false;
		if (!field.getType().isArray()) {
			isObjectArray = false;
		} else {
			isObjectArray = !BasicType.isBasicType(field.getType().getComponentType());
		}
		return isObjectArray;
	}

}
