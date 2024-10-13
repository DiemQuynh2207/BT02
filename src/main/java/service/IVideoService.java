package service;

import java.util.List;

import models.Video;



public interface IVideoService {
	void insert(Video video);
	void update(Video video);
	void delete(String videoid) throws Exception;
	Video findById(String videoid);
	List<Video> findAll();
	List<Video> findByVideoname(String videoname);
	List<Video> findAll(int page, int pagesize);
	int count();
}
