package com.redKango.pss.template;

import java.sql.ResultSet;

/**
 * To universally handle the ResultSet from queries with JavaBean domain.
 * 
 * @author Administrator
 *
 * @param <T> Accepts any type of class for the JavaBean domain.
 */
public interface IResultSetHandler<T> {
	/**
	 * Handling method that turns any ResultSet derived from query into a JavaBean
	 * domain.
	 * 
	 * @param rs This ResultSet from the query that contains the keys and values
	 *           that could be turned into a JavaBean domain.
	 * @return T Return the DO or List of DO that contains the keys and
	 *         corresponding values of the ResultSet
	 */
	T handle(ResultSet rs);
}
