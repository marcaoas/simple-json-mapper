package com.simplejsonmapper;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.simplejsonmapper.types.Type;

@SuppressWarnings("unchecked")
public class ObjectMapper {

	/**
	 * Maps a json string to an Object.
	 * @param jsonString The json representation of  the object. 
	 * @param clazz The return type of the method.  
	 * @return An object of type <b>clazz</b>.
	 * @throws JsonMappingException If the json is not parsable or if any error occurred while binding the values to the object. 
	 */
	public <T> T map(String jsonString, Class<T> clazz) throws JsonMappingException{
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(jsonString);
		} catch (JSONException e) {
			throw new JsonMappingException(e);
		}
		return map(jsonObject, clazz);
	}

	/**
	 * Maps a {@link JSONObject} to an Object.
	 * @param jsonObject The {@link JSONObject} representation of  the object. 
	 * @param clazz The return type of the method.  
	 * @return An object of type <b>clazz</b>.
	 * @throws JsonMappingException If any error occurred while binding the values to the object. 
	 */
	public <T> T map(JSONObject jsonObject, Class<T> clazz) throws JsonMappingException{
		return mapSingleValue(jsonObject, clazz);
	}
	
	
	/**
	 * Maps a json string to an Array of Object.
	 * @param jsonString The json representation of the array. 
	 * @param clazz The type of the returned array.  
	 * @return An Array of objects of type <b>clazz</b>.
	 * @throws JsonMappingException If the json is not parsable or if any error occurred while binding the values to the object array. 
	 */
	public <T> T[] mapArray(String jsonString, Class<T> clazz) throws JsonMappingException{
		JSONArray jsonArray;
		try {
			jsonArray = new JSONArray(jsonString);
		} catch (JSONException e) {
			throw new JsonMappingException(e);
		}
		return mapArray(jsonArray, clazz);
	}


	/**
	 * Maps a {@link JSONArray} to an Array of Object.
	 * @param jsonArray The {@link JSONArray} representation of the array. 
	 * @param clazz The type of the returned array.  
	 * @return An Array of objects of type <b>clazz</b>.
	 * @throws JsonMappingException If the json is not parsable or if any error occurred while binding the values to the object array. 
	 */
	public <T> T[] mapArray(JSONArray jsonArray, Class<T> clazz) throws JsonMappingException {
		T[] values = (T[]) Array.newInstance(clazz, jsonArray.length());
		for (int i = 0; i < values.length; i++) {
			values[i] = mapSingleValue(jsonArray.optJSONObject(i), clazz);
		}
		return values;
	}

	
	private <T> T mapSingleValue(JSONObject jsonObject, Class<T> clazz) throws JsonMappingException {
		T t = null;
		try {
			t = clazz.newInstance();
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				String fieldName = getFieldName(field);
				boolean ignore = getIgnoreFIeld(field);
				if (!ignore && jsonObject.has(fieldName)) {
					Type type = Type.getFieldType(field);
					field.setAccessible(true);
					type.set(field, t,jsonObject.get(fieldName));
				}
			}
		} catch (Exception e) {
			throw new JsonMappingException(e);
		}
		return t;
	}
	
	private boolean getIgnoreFIeld(Field field) {
		IgnoreField annotation = field.getAnnotation(IgnoreField.class);
		return annotation != null;
	}

	private String getFieldName(Field field) {
		JsonField annotation = field.getAnnotation(JsonField.class);
		return annotation != null ? annotation.value() : field.getName();
	}

}
