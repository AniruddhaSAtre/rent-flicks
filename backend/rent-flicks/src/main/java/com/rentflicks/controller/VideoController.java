package com.rentflicks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rentflicks.model.Movie;
import com.rentflicks.model.Video;
import com.rentflicks.service.VideoService;
import java.util.List;

@RestController
public class VideoController {

	VideoService videoService;

	@Autowired
	public VideoController(VideoService videoService) {
		super();
		this.videoService = videoService;
	}

	@RequestMapping(value = "/videos", method = RequestMethod.GET)
	public List<Video> GetVideos() {
		return videoService.getVideos();
	}
	
	@RequestMapping(value = "/videos/user/{userId}", method = RequestMethod.GET)
	public List<Video> GetVideosOfUser(@PathVariable String userId) {
		return videoService.getVideosUser(Integer.parseInt(userId));
	}
	
	@RequestMapping(value = "/videos/request/{userId}", method = RequestMethod.GET)
	public List<Video> GetRequestsOfUser(@PathVariable String userId) {
		return videoService.getRequestsUser(Integer.parseInt(userId));
	}
	
	@RequestMapping(value="/video/add", method=RequestMethod.POST)
	public Video AddVideo(@RequestBody Video video){
		
		return videoService.addVideo(video);
	}


}