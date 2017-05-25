package com.xiaoming.test.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.xiaoming.domain.Pager;
import com.xiaoming.dto.AssignmentDto;
import com.xiaoming.dto.MessageDto;
import com.xiaoming.util.JsonUtil;

import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

public class GsonTest {
//	Gson gson = new Gson();
//	
//	public void testParse(){
//		Pager<MessageDto> page = new Pager<>();
//		page.setBeginPageIndex(1);
//		page.setCurrentPage(1);
//		page.setEndPageIndex(3);
//		page.setPageSize(3);
//		MessageDto m = new MessageDto();
//		m.setId(1l);
//		m.setContent("哈哈，通知");
//		m.setIsRead(false);
//		m.setPublisher("小明");
//		m.setPublishTime(new Date());
//		List<String> r = new ArrayList<>();
//		r.add("一一");
//		r.add("二二");
////		m.setReceivers(r);
//		List<MessageDto> l = new ArrayList<>();
//		l.add(m);
//		l.add(m);
//		page.setRecordList(l);
//		System.out.println(gson.toJson(page));
//	}
//	
//	
//	public void map2Json(){
//		Map<String , String> map = new HashMap<>();
//		map.put("name", "张三");
//		map.put("id", "1");
//		System.out.println(new Gson().toJson(map));
//	}
//	
//	@Test
//	public void test12(){
//		String json = "{\"project\":{\"id\":1},\"exercisers\":[{\"id\":1},{\"id\":2}],\"deadline\":\"\",\"content\":\"工作内容\"}";
//		
////		System.out.println(model);
//	}
	
	public static void main(String[] args) throws Exception {
		JSONObject json = new JSONObject();
		String[] str = {"hello","world"};
		Map<String,String[]> map = new HashMap<>();
		map.put("data", str);
//		json = JSONObject.fromObject(map);
		json = JsonUtil.succObject(map);
		System.out.println(json.toString());
	}
}
