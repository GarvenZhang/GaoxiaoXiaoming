package com.xiaoming.util.dynamicBean;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.sf.json.JSONObject;

@SuppressWarnings("unchecked")
public class ClassUtil {
	public static Object createBeanFromJson(String json){
		HashMap<String,Object> jsonMap = new Gson().fromJson(json, HashMap.class);
		return createBeanFromMap(jsonMap);
	}
	
	public static Object createBeanFromMap(Map<String,Object> map){
		Set<String> propertySet = map.keySet();
		HashMap<String, Class<String>> propertyMap = new HashMap<>();
		for (String prop : propertySet) {
			propertyMap.put(prop, String.class);
		}
		
		DynamicBean bean = new DynamicBean(propertyMap);
		for (String prop : propertySet) {
			bean.setValue(prop, map.get(prop));
		}
		
		return bean.getObject();
	}
	
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Object obj = createBeanFromJson("{\"生日\": \"1992010\",\"大小\": \"M\"}");
		// 通过反射查看所有方法名
		Class clazz = obj.getClass();
		Method[] methods = clazz.getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) {
			if(methods[i].getName().contains("get")){
				System.out.println(methods[i].getName()+": "+methods[i].invoke(obj, null));
			}
		}
	}
}