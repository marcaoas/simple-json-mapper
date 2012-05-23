package com.simplejsonmapper.types;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import com.simplejsonmapper.JsonMappingException;
 
public class ObjectListType extends Type {

	private static ObjectListType INSTANCE;

	@Override
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void set(Field field, Object object, Object value) throws JsonMappingException {
		try {
			JSONArray objectJsonArray = (JSONArray) value;
			
			List list = new ArrayList();
			for (int i = 0; i < objectJsonArray.length(); i++) {
				ParameterizedType type =  (ParameterizedType)field.getGenericType();
				list.add(MAPPER.map(objectJsonArray.getJSONObject(i),(Class)type.getActualTypeArguments()[0]));
			}
			field.set(object, list);
		} catch (Exception e) {
			throw new JsonMappingException(e);
		}

	}

	public static Type getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ObjectListType();
		}
		return INSTANCE;
	}

	public static boolean isObjectList(Field field) {
		return List.class.isAssignableFrom(field.getType());
	}

}
