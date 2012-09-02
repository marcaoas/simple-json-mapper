package com.simplejsonmapper.types;

import java.lang.reflect.Field;

import com.simplejsonmapper.JsonMappingException;
import com.simplejsonmapper.ObjectMapper;

public abstract class Type {

	protected static ObjectMapper MAPPER = new ObjectMapper();

	public static Type getFieldType(Field field) {
		Type type = null;
		if (BasicType.isBasicType(field)) {
			type = BasicType.getInstance();
		} else if (BasicArrayType.isBasicArray(field)) {
			type = BasicArrayType.getInstance();
		} else if (ObjectListType.isObjectList(field)) {
			type = ObjectListType.getInstance();
		}
		return type;
	}

	public abstract void set(Field f, Object object, Object value) throws JsonMappingException;

}
