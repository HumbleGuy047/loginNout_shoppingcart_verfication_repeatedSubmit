package com.redKango.pss.template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.redKango.pss.util.JdbcUtil;

public class JdbcTemplate {
	private JdbcTemplate() {
	}

	/**
	 * DML execution.
	 * 
	 * @param sql    Indicates which action is to be undertaken:
	 *               create/delete/update.
	 * @param params Each value of the table columns needed to execute the SQL.
	 */
	public static void manipulate(String sql, Object... params) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			// get connection and create statement
			conn = JdbcUtil.getConn();
			ps = conn.prepareStatement(sql);
			// match values from the object's fields with placeholders in the SQL
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			// execute statement
			System.out.println("Executed " + ps.executeUpdate() + " line(s).");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// close resources
			JdbcUtil.close(conn, ps, null);
		}
	}

	/**
	 * DQL execution.
	 * 
	 * @param sql    Indicates to read a single/all row(s).
	 * @param rsh    A ResultSetHandler object that matches data from the table with
	 *               the field of the object.
	 * @param params Other parameters such as id which will be needed for
	 *               single_reading_SQL
	 * @return Could be an object or a list of object
	 */
	public static <T> T query(String sql, IResultSetHandler<T> rsh, Object... params) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConn();
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			// execute statement and store the returned ResultSet
			rs = ps.executeQuery();
			// move data from ResultSet to the fields of the object
			return rsh.handle(rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, ps, rs);
		}
		return null;
	}
}
