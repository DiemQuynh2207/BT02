package service.impl;

import java.util.List;

import dao.ICategoryDao;
import dao.IVideoDao;
import dao.impl.VideoDaoImpl;
import models.Video;
import service.IVideoService;

public class VideoService implements IVideoService {
	IVideoDao videoDao = new VideoDaoImpl();
	@Override
	public void insert(Video video) {
		// TODO Auto-generated method stub
		videoDao.insert(video);
	}

	@Override
	public void update(Video video) {
		// TODO Auto-generated method stub
		videoDao.update(video);
	}

	@Override
	public void delete(String videoid) throws Exception {
		// TODO Auto-generated method stub
		videoDao.delete(videoid);
	}

	@Override
	public Video findById(String videoid) {
		// TODO Auto-generated method stub
		return videoDao.findById(videoid);
	}

	@Override
	public List<Video> findAll() {
		// TODO Auto-generated method stub
		return videoDao.findAll();
	}

	@Override
	public List<Video> findByVideoname(String videoname) {
		// TODO Auto-generated method stub
		return videoDao.findByVideoname(videoname);
	}

	@Override
	public List<Video> findAll(int page, int pagesize) {
		// TODO Auto-generated method stub
		return videoDao.findAll(page, pagesize);
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return videoDao.count();
	}

}
