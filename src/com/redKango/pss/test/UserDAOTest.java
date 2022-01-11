package com.redKango.pss.test;

import org.junit.Test;

import com.redKango.pss.dao.IUserDAO;
import com.redKango.pss.dao.impl.UserDAOImpl;
import com.redKango.pss.domain.User;

public class UserDAOTest {
	private IUserDAO dao = new UserDAOImpl();

	@Test
	public void testCheckLogin() {
		User u = dao.checkLogin("admin", "12342223");
		System.out.println(u);
	}

}
