package dao.impl;

import java.util.List;

import configs.JPAConfig;
import dao.IUserDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import models.User;

public class UserDaoImpl implements IUserDao{

	@Override
	public void insert(User user) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(user);
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
	public void update(User user) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.merge(user);
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
	public void delete(String username) throws Exception {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			User user = enma.find(User.class, username);
			if(user != null) {
				enma.remove(user);
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
	public User findById(String username) {
		EntityManager enma = JPAConfig.getEntityManager();
		User user = enma.find(User.class, username);
		return user;
	}

	@Override
	public List<User> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<User> query = enma.createNamedQuery("User.findAll", User.class);
		return query.getResultList();
	}

	@Override
	public User findByUsername(String username) {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "Select c from User c where c.username like :username";
		TypedQuery<User> query = enma.createQuery(jpql, User.class);
		query.setParameter("username", "%" + username + "%");
		return query.getSingleResult();
	}

	@Override
	public boolean checkExistEmail(String email) {
		User user = null;
		user = this.findByEmail(email);
		if (user == null)
			return false;
		return true;
	}
	
	@Override
	public User findByEmail(String email) {
		EntityManager enma = JPAConfig.getEntityManager();
		User user = enma.find(User.class, email);
		return user;
	}

	@Override
	public boolean checkExistUsername(String username) {
		User user = null;
		user = this.findByUsername(username);
		if (user == null)
			return false;
		return true;
	}

}
