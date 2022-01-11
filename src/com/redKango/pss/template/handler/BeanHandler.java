package com.redKango.pss.template.handler;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.sql.ResultSet;

import com.redKango.pss.domain.Column;
import com.redKango.pss.template.IResultSetHandler;

/**
 * Write the data from the table into the fields of the domain object.
 * 
 * @author Administrator
 *
 * @param <T> Introduce the any class type of the DO.
 */
public class BeanHandler<T> implements IResultSetHandler<T> {
	private Class<T> classType;

	// get the class type of the domain object
	public BeanHandler(Class<T> classType) {
		this.classType = classType;
	}

	@Override
	public T handle(ResultSet rs) {
		try {
			T obj = classType.newInstance(); // create domain object from classType
			// if the ResultSet isn't empty
			if (rs.next()) {
				// reflection
				BeanInfo beanInfo = Introspector.getBeanInfo(classType, Object.class);
				PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
				for (PropertyDescriptor pd : pds) {
					String keyName = pd.getName();
					//==============Check for alias of column names===============
					Field field = classType.getDeclaredField(keyName);
					if (field.isAnnotationPresent(Column.class)) {
						keyName = field.getDeclaredAnnotation(Column.class).value();
					}
					//============================================================
					Object value = rs.getObject(keyName);
					// use writeMethod to write data from resultSet into fields of DO
					pd.getWriteMethod().invoke(obj, value);
				}
			}
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
