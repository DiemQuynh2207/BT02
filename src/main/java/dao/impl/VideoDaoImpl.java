package dao.impl;

import java.util.List;

import configs.JPAConfig;
import dao.IVideoDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import models.Category;
import models.Video;

public class VideoDaoImpl implements IVideoDao{

	@Override
	public void insert(Video video) {
		// TODO Auto-generated method stub
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(video);
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
	public void update(Video video) {
		// TODO Auto-generated method stub
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.merge(video);
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
	public void delete(String videoid) throws Exception {
		// TODO Auto-generated method stub
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			Video video = enma.find(Video.class, videoid);
			if(video != null) {
				enma.remove(video);
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
	public Video findById(String videoid) {
		// TODO Auto-generated method stub
		EntityManager enma = JPAConfig.getEntityManager();
		Video video = enma.find(Video.class, videoid);
		return video;
	}

	@Override
	public List<Video> findAll() {
		// TODO Auto-generated method stub
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Video> query = enma.createNamedQuery("Video.findAll", Video.class);
		return query.getResultList();
	}

	@Override
	public List<Video> findByVideoname(String videoname) {
		// TODO Auto-generated method stub
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "Select v from Video v where v.videoname like :videoname";
		TypedQuery<Video> query = enma.createQuery(jpql, Video.class);
		query.setParameter("videoname", "%" + videoname + "%");
		return query.getResultList();
	}

	@Override
	public List<Video> findAll(int page, int pagesize) {
		// TODO Auto-generated method stub
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Video> query = enma.createNamedQuery("Video.findAll", Video.class);
		query.setFirstResult(page*pagesize);
		query.setMaxResults(pagesize);
		return query.getResultList();
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "Select count(c) from Video c";
		Query query = enma.createQuery(jpql);
		return ((Long)query.getSingleResult()).intValue();
	}

}
