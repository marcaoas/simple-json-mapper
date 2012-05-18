package com.simplejsonmapper.types;

import java.lang.reflect.Field;

import com.simplejsonmapper.JsonMappingException;

public abstract class Type {
	
	public static Type getFieldType(Field field){
		Type type = null;
		if(BasicType.isBasicType(field)){
			type = BasicType.getInstance();
		}else if(BasicArrayType.isBasicArray(field)){
			type = BasicArrayType.getInstance();
		}
		return type;
	}
	
	public abstract void set(Field f, Object object, Object value) throws JsonMappingException;
	
}
