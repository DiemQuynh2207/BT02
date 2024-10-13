package service.impl;

import java.util.List;


import dao.IUserDao;
import dao.impl.UserDaoImpl;
import models.User;
import service.IUserService;

public class UserService implements IUserService {
	IUserDao userDao = new UserDaoImpl();

	@Override
	public User login(String username, String password) {
		User user = this.get(username);
		if (user != null && password.equals(user.getPassword())) {
			return user;
		}
		return null;
	}

	@Override
	public boolean register(String username, String email, String phone, String password, String fullname) {
		if (checkExistEmail(email) || checkExistUsername(username))
			return false;
		userDao.insert(new User(username, false, false, email, phone, fullname, password, ""));
		return true;
	}
	
	@Override
	public User get(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public User update(String username, String fullname, String email) {
//		User user = userDao.findByUsername(username);
//		user.setFullname(fullname);
//		user.setEmail(email);
//		userDao.update(user);
		return null;
	}

	@Override
	public User updateImage(String username, String image) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean checkExistEmail(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkExistUsername(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updatePassword(String email, String password) {
		// TODO Auto-generated method stub
		return false;
	}

}
