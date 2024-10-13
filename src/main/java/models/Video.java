package models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "videos")
@NamedQuery(name = "Video.findAll", query = "Select v from Video v")
public class Video implements Serializable{
	private static final long serialVersionUID = -74014124559999475L;
	
	@Id
	@Column(name="VideoId")
	private String videoId;
	
	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getViews() {
		return views;
	}

	public void setViews(String views) {
		this.views = views;
	}

	@Column(name="Active")
	private String active;
	
	@Column(name="Description", columnDefinition = "nvarchar(500) NULL")
	private String description;
	
	@Column(name="Poster", columnDefinition = "nvarchar(500) NULL")
	private String poster;
	
	@Column(name="TitleVideo", columnDefinition = "nvarchar(500) NULL")
	private String title;
	
	@Column(name="Views")
	private String views;
	
	@ManyToOne
	@JoinColumn(name="CategoryId")
	private Category category;

	public Video() {
	}

}


