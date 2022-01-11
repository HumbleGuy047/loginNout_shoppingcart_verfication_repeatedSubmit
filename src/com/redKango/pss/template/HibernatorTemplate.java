package com.redKango.pss.template;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.redKango.pss.domain.Column;
import com.redKango.pss.domain.ID;
import com.redKango.pss.domain.Table;

/**
 * A class to build DML SQLs for a table. An object stores a row of data;
 * methods in this class access the values of the data through reflection. Each
 * declared field in the object is tagged with an annotation with a value that
 * coincide with each column name in the table. Matching the values stored in
 * the object and the corresponding column names in the table, DML SQLs are
 * easily constructed.
 * 
 * @author Administrator
 *
 */
public class HibernatorTemplate {
	private HibernatorTemplate() {
	}

	public static void create(Object obj) {
		/*To replace this: String sql = "INSERT INTO `product`(productName, dir_id, salePrice, supplier, brand, discount, costPrice) VALUES (?, ?, ?, ?, ?, ?, ?)"; // sql using ? as placeholders
		Object[] params = { pro.getProductName(), pro.getDir_id(), pro.getSalePrice(), pro.getSupplier(),
				pro.getBrand(), pro.getDiscount(), pro.getCostPrice() };
		JdbcTemplate.manipulate(sql, params);*/
		try {
			StringBuilder sql = new StringBuilder(100);
			StringBuilder colNames = new StringBuilder(8);
			StringBuilder placeHolders = new StringBuilder(7);
			List<Object> params = new ArrayList<>();
			String tableName = obj.getClass().getSimpleName();
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass(), Object.class);
			PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();

			// If there is a specific table name expressed in the annotation of the object's type, then use that name in the SQL
			if (obj.getClass().isAnnotationPresent(Table.class)) {
				tableName = obj.getClass().getAnnotation(Table.class).value();
			}

			// Each field's value and its annotation's value are accessed. Each set of these info is put into a StringBuilder for later.
			for (PropertyDescriptor pd : pds) {
				String keyName = pd.getName();
				Field field = obj.getClass().getDeclaredField(keyName);
				if (!field.isAnnotationPresent(ID.class)) {
					if (field.isAnnotationPresent(Column.class)) {
						keyName = field.getDeclaredAnnotation(Column.class).value();
					}
					params.add(pd.getReadMethod().invoke(obj));
					colNames.append("`" + keyName + "`,");
					placeHolders.append("?,");
				}
			}
			// Delete the redundant last comma.
			colNames.delete(colNames.length() - 1, colNames.length());
			placeHolders.delete(placeHolders.length() - 1, placeHolders.length());

			// Building SQL
			sql.append("INSERT INTO ");
			sql.append("`").append(tableName).append("` (");
			sql.append(colNames);
			sql.append(") VALUES (");
			sql.append(placeHolders);
			sql.append(")");

			/*System.out.println(sql.toString());
			for (Object param : params) {
				System.out.println(param);
			}*/
			JdbcTemplate.manipulate(sql.toString(), params.toArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void delete(Long id, Object obj) {
		/*String sql = "DELETE FROM `product` WHERE id = ?";
		JdbcTemplate.manipulate(sql, id);*/

		StringBuilder sql = new StringBuilder(100);
		String tableName = obj.getClass().getSimpleName();

		if (obj.getClass().isAnnotationPresent(Table.class)) {
			tableName = obj.getClass().getAnnotation(Table.class).value();
		}

		sql.append("DELETE FROM ");
		sql.append("`" + tableName + "`");
		sql.append(" WHERE id = ?");

		//System.out.println(sql.toString());
		JdbcTemplate.manipulate(sql.toString(), id);

	}

	public static void update(Object obj) {
		/*String sql = "UPDATE `product` SET productName = ?, dir_id = ? , salePrice = ?, supplier = ?, brand = ?, discount = ?, costPrice = ? WHERE id = ?";
		Object[] params = { pro.getProductName(), pro.getDir_id(), pro.getSalePrice(), pro.getSupplier(),
				pro.getBrand(), pro.getDiscount(), pro.getCostPrice(), pro.getId() };
		JdbcTemplate.manipulate(sql, params);*/
		try {
			StringBuilder sql = new StringBuilder(100);
			StringBuilder updates = new StringBuilder(8);
			List<Object> params = new ArrayList<>();
			String tableName = obj.getClass().getSimpleName();
			Long id = 0L;

			// Get all column names into a list.
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass(), Object.class);
			PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();

			if (obj.getClass().isAnnotationPresent(Table.class)) {
				tableName = obj.getClass().getAnnotation(Table.class).value();
			}

			for (PropertyDescriptor pd : pds) {
				String keyName = pd.getName();
				Field field = obj.getClass().getDeclaredField(keyName);
				if (!field.isAnnotationPresent(ID.class)) {
					if (field.isAnnotationPresent(Column.class)) {
						keyName = field.getDeclaredAnnotation(Column.class).value();
					}
					params.add(pd.getReadMethod().invoke(obj));
					updates.append("`" + keyName + "` = ?,");

				}
				else {
					id = (Long) pd.getReadMethod().invoke(obj);
				}
			}
			updates.delete(updates.length() - 1, updates.length());

			sql.append("UPDATE ");
			sql.append("`").append(tableName).append("` ");
			sql.append(" SET ");
			sql.append(updates);
			sql.append(" WHERE id = " + id);
			

			/*System.out.println(sql.toString());
			for (Object param : params) {
				System.out.println(param);
			}*/
			JdbcTemplate.manipulate(sql.toString(), params.toArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}
