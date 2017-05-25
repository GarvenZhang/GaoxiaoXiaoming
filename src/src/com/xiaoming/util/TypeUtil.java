/**
 * @author JJ
 * 判断类型的方法
 * 2016/4/20
 */
package com.xiaoming.util;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.xiaoming.constants.Constants;

public class TypeUtil {
	// 返回类型相等的属性
	public static <T> Set<String> getEqualsTypeFile(T c, T t,
			ArrayList<String> typeName) throws InstantiationException,
			IllegalAccessException {
		Field[] fs = c.getClass().getDeclaredFields();
		Type f = null;
		Set<String> fields = new HashSet<String>();
		// (ParameterizedType)Clazz.class.getDeclaredField("clazzTime_tables").getGenericType();
		String type = "";
		String typeStr = "";
		typeName.add(t.getClass().getName());

		for (int i = 0; i < fs.length; i++) {
			f = fs[i].getGenericType();
			typeStr = f.toString();
			if (typeStr.contains(Constants.PACKAGE_NAME)
					&& !(typeStr.contains("java.util.Set"))) {
				typeName.add(typeStr.substring(6));
				fields.addAll(getEqualsTypeFile(fs[i].getType().newInstance(),
						t, typeName));

			}
			if (typeStr.contains("java.util.Set")) {
				ParameterizedType pt = (ParameterizedType) f;
				type = pt.getActualTypeArguments()[0].toString().substring(6);
				for (String tstr : typeName) {
					fields.add(fs[i].getName());

				}

			}
		}
		return fields;
	}
}
