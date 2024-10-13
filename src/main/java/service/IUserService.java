package service;

import java.util.List;

import models.User;

public interface IUserService {
	public User login(String username, String password);

	public User get(String username);
	
	public User update(String username, String fullname, String email);
	
	public User updateImage(String username, String image);

	boolean register(String username, String email,String phone, String password, String fullname);

	boolean checkExistEmail(String email);

	boolean checkExistUsername(String username);
	
	boolean updatePassword(String email, String password);
}
