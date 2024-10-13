package service;

import java.util.List;

import models.Category;

public interface ICategoryService {
	void insert(Category category);
	void update(Category category);
	void delete(int cateid) throws Exception;
	Category findById(int cateid);
	List<Category> findAll();
	List<Category> findByCategoryname(String catename);
	List<Category> findAll(int page, int pagesize);
	int count();
}
