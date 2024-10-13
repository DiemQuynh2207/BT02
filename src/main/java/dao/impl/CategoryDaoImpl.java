package dao.impl;

import java.util.List;


import configs.JPAConfig;
import dao.ICategoryDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import models.Category;

public class CategoryDaoImpl implements ICategoryDao {

	@Override
	public void insert(Category category) {
		// TODO Auto-generated method stub
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(category);
			trans.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
			
		}finally {
			enma.close();
		}
	}

	@Override
	public void update(Category category) {
		// TODO Auto-generated method stub
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.merge(category);
			trans.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
			
		}finally {
			enma.close();
		}
	}

	@Override
	public void delete(int cateid) throws Exception {
		// TODO Auto-generated method stub
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			Category category = enma.find(Category.class, cateid);
			if(category != null) {
				enma.remove(category);
			}
			else {
				throw new Exception("Không tìm thấy");
			}
			trans.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
			
		}finally {
			enma.close();
		}
	}

	@Override
	public Category findById(int cateid) {
		// TODO Auto-generated method stub
		EntityManager enma = JPAConfig.getEntityManager();
		Category category = enma.find(Category.class, cateid);
		return category;
	}

	@Override
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Category> query = enma.createNamedQuery("Category.findAll", Category.class);
		return query.getResultList();
	}

	@Override
	public List<Category> findByCategoryname(String catename) {
		// TODO Auto-generated method stub
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "Select c from Category c where c.catename like :catename";
		TypedQuery<Category> query = enma.createQuery(jpql, Category.class);
		query.setParameter("catename", "%" + catename + "%");
		return query.getResultList();
	}

	@Override
	public List<Category> findAll(int page, int pagesize) {
		// TODO Auto-generated method stub
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Category> query = enma.createNamedQuery("Category.findAll", Category.class);
		query.setFirstResult(page*pagesize);
		query.setMaxResults(pagesize);
		return query.getResultList();
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "Select count(c) from Category c";
		Query query = enma.createQuery(jpql);
		return ((Long)query.getSingleResult()).intValue();
	}

}
