package com.simplejsonmapper.types;

import java.lang.reflect.Field;

import com.simplejsonmapper.JsonMappingException;

@SuppressWarnings("rawtypes")
public class BasicType extends Type {

	protected static Class[] basicTypes = { int.class, Integer.class, Double.class, double.class, Float.class, float.class,
		Boolean.class, boolean.class, String.class, Long.class, long.class };
	
	private static BasicType INSTANCE;
	
	private BasicType() {
	}
	
	public static Type getInstance() {
		if(INSTANCE == null){
			INSTANCE = new BasicType();
		}
		return INSTANCE;
	}
	
	@Override
	public void set(Field field, Object object, Object value) throws JsonMappingException {
		try {
			field.set(object, value);
		} catch (Exception e) {
			throw new JsonMappingException(e);
		}
	}

	public static boolean isBasicType(Field field) {
		return isBasicType(field.getType());
	}
	
	public static boolean isBasicType(Class type) {
		boolean contains = false;
		for (Class clazz : basicTypes) {
			if (type.equals(clazz)) {
				contains = true;
				break;
			}
		}
		return contains;
	}

}
