package com.redKango.pss.dao.impl;

import com.redKango.pss.dao.IUserDAO;
import com.redKango.pss.domain.User;
import com.redKango.pss.template.JdbcTemplate;
import com.redKango.pss.template.handler.BeanHandler;

public class UserDAOImpl implements IUserDAO {

	@Override
	public User checkLogin(String username, String password) {
		return JdbcTemplate.query("SELECT * FROM `t_user` WHERE username = ? AND password = ?",
				new BeanHandler<User>(User.class), username, password);
	}

}
