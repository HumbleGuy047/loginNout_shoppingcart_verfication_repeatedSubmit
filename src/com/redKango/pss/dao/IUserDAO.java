package com.redKango.pss.dao;

import com.redKango.pss.domain.User;

public interface IUserDAO {

	/**
	 * Login verification
	 * 
	 * @param username Username.
	 * @param password Password.
	 * @return Returns the user's data.
	 */
	User checkLogin(String username, String password);
}
