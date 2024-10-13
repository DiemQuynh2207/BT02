package dao;

import java.util.List;

import models.User;


public interface IUserDao {
	void insert(User user);
	void update(User user);
	void delete(String username) throws Exception;
	User findById(String username);
	List<User> findAll();
	User findByUsername(String username);
	boolean checkExistEmail(String email);
	boolean checkExistUsername(String username);
	User findByEmail(String email);
}
