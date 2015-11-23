package com.rentflicks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentflicks.model.Video;
import com.rentflicks.repository.VideoRepository;

@Service
public class VideoService {

	VideoRepository videoRepository;

	@Autowired
	public VideoService(VideoRepository videoRepository) {
		super();
		this.videoRepository = videoRepository;
	}

	public List<Video> getVideos() {
		List<Video> videos = videoRepository.findAll();
		return videos;
	}
	
	public List<Video> getVideosUser(Integer ownerId) {
		List<Video> videos = videoRepository.findByOwnerId(ownerId);
		return videos;
	}
	
	public List<Video> getRequestsUser(Integer userId){
		List<Video> videos = videoRepository.findByRequestsBorrowerUserId(userId);
		return videos;
	}
	
	public Video getVideoById(Integer id){
		return videoRepository.findOne(id);
	}
	
	public Video addVideo(Video video){
		return videoRepository.save(video);
	}

}
