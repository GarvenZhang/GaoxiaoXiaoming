package com.xiaoming.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;

import com.xiaoming.constants.Constants;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

public class GetModelByJson {
	@SuppressWarnings("unchecked")
	public static <T> T getModel(HttpServletRequest request, T t) throws Exception {
		// 处理json数据
		String objectString = IOUtils.toString(request.getInputStream(), "utf-8");
		if (objectString.equals("")) {
			return t;
		}
		return getModelByString(objectString, t);
	}

	public static <T> T getModel(HttpServletRequest request, T t, Map<String, Class> classMap) throws Exception {
		// 处理json数据
		String objectString = IOUtils.toString(request.getInputStream(), "utf-8");
		if (objectString.equals("")) {
			return t;
		}
		return getModelByString(objectString, t, classMap);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getModelByString(String jsonStirng, T t) throws Exception {
		// // 处理json数据
		// JSONObject jsonObject = JSONObject.fromObject(jsonStirng);
		// jsonObject = changeDate(jsonObject);
		// // 将json数据映射为model对象
		// t = (T) JSONObject.toBean(jsonObject, t.getClass());
		// return t;
		return getModelByString(jsonStirng, t, null);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getModelByString(String jsonStirng, T t, Map<String, Class> classMap) throws Exception {
		// 处理json数据
		JSONObject jsonObject = JSONObject.fromObject(jsonStirng);
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] { Constants.DATE_FORMAT }));
		// jsonObject = changeDate(jsonObject);
		// 将json数据映射为model对象
		t = (T) JSONObject.toBean(jsonObject, t.getClass(), classMap);
		return t;
	}

	public static JSONObject changeDate(JSONObject json) throws ParseException {
		// 将所有关于日期的变量都加上_time后缀，用于日期格式的映射
		@SuppressWarnings("unchecked")
		Set<String> key = json.keySet();
		for (String k : key) {
			if (k.contains(Constants.DOMAIN_DATE_FORMAT)) {
				json.put(k, DateStringFormat.StringFormatDate((String) json.get(k), Constants.DATE_FORMAT));
			}
		}
		return json;
	}

	public static <T> ArrayList<T> getList(Set<T> tSet, T t) throws Exception {
		ArrayList<T> tList = new ArrayList<T>(tSet);
		ArrayList<T> resultList = new ArrayList<>();
		JSONObject json = null;
		for (int i = 0; i < tList.size(); i++) {
			json = JSONObject.fromObject(tList.get(i));
			t = getModelByString(json.toString(), t);
			resultList.add(t);
		}
		return resultList;
	}

	/**
	 * 将一个输入流转化为字符串
	 */
	private static String getStreamString(InputStream tInputStream,String encode) {
		if (tInputStream != null) {
			try {
				BufferedReader tBufferedReader = new BufferedReader(new InputStreamReader(tInputStream,encode));
				StringBuffer tStringBuffer = new StringBuffer();
				String sTempOneLine = new String("");
				System.out.println("bufferedReader:" + tBufferedReader);
				while ((sTempOneLine = tBufferedReader.readLine()) != null) {
					tStringBuffer.append(sTempOneLine);
				}
				return tStringBuffer.toString();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

}
