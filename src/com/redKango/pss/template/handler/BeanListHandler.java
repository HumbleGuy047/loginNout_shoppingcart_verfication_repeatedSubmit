package com.redKango.pss.template.handler;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.redKango.pss.domain.Column;
import com.redKango.pss.template.IResultSetHandler;

public class BeanListHandler<T> implements IResultSetHandler<List<T>> {
	private Class<T> classType;

	public BeanListHandler(Class<T> classType) {
		this.classType = classType;
	}

	@Override
	public List<T> handle(ResultSet rs) {
		try {
			List<T> list = new ArrayList<>();
			while (rs.next()) {
				T obj = classType.newInstance();
				BeanInfo beanInfo = Introspector.getBeanInfo(classType, Object.class);
				PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
				for (PropertyDescriptor pd : pds) {
					String keyName = pd.getName();
					//==============Check for alias of column names===============		
					Field field = classType.getDeclaredField(keyName);
					if (field.isAnnotationPresent(Column.class)) {
						keyName = field.getDeclaredAnnotation(Column.class).value();
						//System.out.println(keyName);
					}
					//============================================================
					Object value = rs.getObject(keyName);
					pd.getWriteMethod().invoke(obj, value);
				}
				list.add(obj);
			}
			return list;
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

}
