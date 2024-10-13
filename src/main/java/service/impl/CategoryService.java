package service.impl;

import java.util.List;

import dao.ICategoryDao;
import dao.impl.CategoryDaoImpl;
import models.Category;
import service.ICategoryService;

public class CategoryService implements ICategoryService{
	ICategoryDao cateDao = new CategoryDaoImpl();

	@Override
	public void insert(Category category) {
		cateDao.insert(category);
		
	}

	@Override
	public void update(Category category) {
		cateDao.update(category);
	}

	@Override
	public void delete(int cateid) throws Exception {
		cateDao.delete(cateid);
		
	}

	@Override
	public Category findById(int cateid) {
		return cateDao.findById(cateid);
	}

	@Override
	public List<Category> findAll() {
		return cateDao.findAll();
	}

	@Override
	public List<Category> findByCategoryname(String catename) {
		return cateDao.findByCategoryname(catename);
	}

	@Override
	public List<Category> findAll(int page, int pagesize) {
		return cateDao.findAll(page, pagesize);
	}

	@Override
	public int count() {
		return cateDao.count();
	}

	
}
