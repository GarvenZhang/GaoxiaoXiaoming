package com.xiaoming.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.PropertyFilter;

public class JsonUtil {
	// json格式配置器
	private static JsonConfig config = new JsonConfig();
	// 日期格式配置器
	private static JsonDateValueProcessor jsonDateValueProcessornew = new JsonDateValueProcessor();

	// 去除为null的值改为""
	private static JSONObject removeNull(JSONObject json) {
		@SuppressWarnings("unchecked")
		Set<String> key = json.keySet();
		JSONObject j = null;
		JSONArray jsonArray = new JSONArray();
		for (String k : key) {
			if (json.get(k) instanceof JSONNull) {
				json.put(k, "");
			}
			if (json.get(k) instanceof JSONObject) {

				json.put(k, removeNull((JSONObject) json.get(k)));
			}
			if (json.get(k) instanceof JSONArray) {
				for (Object obj : (JSONArray) json.get(k)) {
					j = (JSONObject) obj;
					jsonArray.add(removeNull(j));
				}
				json.put(k, jsonArray);
			}
		}

		return json;
	}

	// 转换日期格式
	// private static final JSONObject formatDate(JSONObject json) {
	// @SuppressWarnings("unchecked")
	// Set<String> key = json.keySet();
	// for (String k : key) {
	// if (k.contains(Constants.DOMAIN_DATE_FORMAT)) {
	// json.put(k, DateStringFormat.DateFormatString(
	// (Date) json.get(k), Constants.DATE_FORMAT));
	// }
	// }
	//
	// return json;
	// }

	public static JSONObject succObject(Object object, String... fieldsList) throws Exception {
		// 注册配置器
		config.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		Set<String> s = TypeUtil.getEqualsTypeFile(object, object, new ArrayList<String>());
		String[] fields = new String[s.size()];
		config.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		config.addIgnoreFieldAnnotation(JsonIgnore.class);
		config.setIgnoreDefaultExcludes(false); // 设置默认忽略
		if (fieldsList != null) {
			for (int i = 0; i < fieldsList.length; i++) {
				s.add(fieldsList[i]);
			}
		}
		config.setExcludes(s.toArray(fields)); // 此处是亮点，只要将所需忽略字段加到数组
		JSONObject jsonObject = JSONObject.fromObject(object, config);
		// 忽略空值的字段
		// PropertyFilter filter = new PropertyFilter() {
		// public boolean apply(Object object, String fieldName, Object
		// fieldValue) {
		// return null == fieldValue;
		// }
		// };
		// config.setJsonPropertyFilter(filter);
		jsonObject.put("status", "true");
		return removeNull(jsonObject);
	}
	
	public static JSONObject succArray(Object[] objArr, String... fieldsList) throws Exception {
		ArrayList<JSONObject> jsonList = new ArrayList<>();
		//如果list中有元素
		if (objArr!=null && objArr.length > 0) {
			// 注册配置器
			Set<String> s = TypeUtil.getEqualsTypeFile(objArr[0], objArr[0], new ArrayList<String>());
			String[] fields = new String[s.size()];
			config.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
			config.setIgnoreDefaultExcludes(false); // 设置默认忽略
			if (fieldsList != null) {
				for (int i = 0; i < fieldsList.length; i++) {
					s.add(fieldsList[i]);
				}
			}
			config.addIgnoreFieldAnnotation(JsonIgnore.class);
			config.setExcludes(s.toArray(fields)); // 此处是亮点，只要将所需忽略字段加到数组
			// 忽略空值的字段
			PropertyFilter filter = new PropertyFilter() {
				public boolean apply(Object object, String fieldName, Object fieldValue) {
					return null == fieldValue;
				}
			};
			config.setJsonPropertyFilter(filter);
			for (Object t : objArr) {
				JSONObject j = JSONObject.fromObject(t, config);
				j = removeNull(j);
				jsonList.add(j);
			}
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("data", jsonList);
		jsonObject.put("status", "true");
		return jsonObject;
	}

	public static <T> JSONObject succList(List<T> list, String... fieldsList)
			throws InstantiationException, IllegalAccessException {
		ArrayList<JSONObject> jsonList = new ArrayList<>();
		//如果list中有元素
		if (list!=null && list.size() > 0) {
			// 注册配置器
			Set<String> s = TypeUtil.getEqualsTypeFile(list.get(0), list.get(0), new ArrayList<String>());
			String[] fields = new String[s.size()];
			config.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
			config.setIgnoreDefaultExcludes(false); // 设置默认忽略
			if (fieldsList != null) {
				for (int i = 0; i < fieldsList.length; i++) {
					s.add(fieldsList[i]);
				}
			}
			config.addIgnoreFieldAnnotation(JsonIgnore.class);
			config.setExcludes(s.toArray(fields)); // 此处是亮点，只要将所需忽略字段加到数组
			// 忽略空值的字段
			PropertyFilter filter = new PropertyFilter() {
				public boolean apply(Object object, String fieldName, Object fieldValue) {
					return null == fieldValue;
				}
			};
			config.setJsonPropertyFilter(filter);
			for (T t : list) {
				JSONObject j = JSONObject.fromObject(t, config);
				j = removeNull(j);
				jsonList.add(j);
			}
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("data", jsonList);
		jsonObject.put("status", "true");
		return jsonObject;
	}

	public static JSONObject fail(String error) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", "false");
		jsonObject.put("error", error);
		return jsonObject;
	}

	public static JSONObject fail() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", "false");
		return jsonObject;
	}

	public static JSONObject succ() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", "true");
		return jsonObject;
	}

	// 把list中的所有对象转成JSONObject
	public static <T> ArrayList<JSONObject> changeToJSONObject(List<T> objects, String... fields) throws Exception {
		ArrayList<JSONObject> jsonObjects = new ArrayList<>();
		for (T t : objects) {
			jsonObjects.add(JsonUtil.succObject(t, fields));
		}
		return jsonObjects;
	}

}
