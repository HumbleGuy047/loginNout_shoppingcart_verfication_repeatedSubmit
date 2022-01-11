package com.redKango.pss.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class JdbcUtil {
	// privatize class name to prohibit being instantiated
	private JdbcUtil() {
	}
	
	private static DataSource dataSource; // DataSource object

	static {
		// load properties file to access variables of the database server such as class name of the connection driver, url, username, password.
		try {
			Properties p = new Properties(); // Properties object
			ClassLoader loader = Thread.currentThread().getContextClassLoader(); // let the current thread get Class Loader
			InputStream in = loader.getResourceAsStream("db.properties"); // load the properties into JVM as an input stream
			p.load(in); // let properties object load the InputStream

			dataSource = DruidDataSourceFactory.createDataSource(p); // use DataSourceFactory to create dataSource with the login info of the properties object
		} catch (Exception e) {
			throw new RuntimeException("Couldn't load file db.properties under classpath.", e);
		}

	}

	// return a connection object
	public static Connection getConn() {
		try {
			// use dataSource to get connection
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		throw new RuntimeException("Database Connection Exception");
	}


	// close all resources, if one resource is missing/unnecessary (like ResultSet in DML, it can be null)
	public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception e3) {
				e3.printStackTrace();
			} finally {
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (Exception e4) {
					e4.printStackTrace();
				}
			}
		}
	}
}
