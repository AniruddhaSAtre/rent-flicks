package com.rentflicks.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rentflicks.RentFlicksApplication;
import com.rentflicks.model.Video;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RentFlicksApplication.class)
public class TestVideoService {

	@Autowired
	private VideoService videoService;

	// Add WebApplicationContext field here.

	// The setUp() method is omitted.

	public boolean compareVideos(Video first, Video second){
		boolean videoIdCheck = first.getVideoId().equals(second.getVideoId());
		boolean movieIdCheck = first.getMovieId().equals(second.getMovieId());
		boolean ownerIdCheck = first.getOwnerId().equals(second.getOwnerId());
		if(videoIdCheck && movieIdCheck && ownerIdCheck)
			return true;
		return false;
	}
	
	@Test
	public void testGetVideos() throws Exception {
		boolean condition = false;
		List<Video> videos = videoService.getVideos();
		Video v = videos.get(1);
		Video resp = videoService.getVideoById(v.getVideoId());
		if (compareVideos(v, resp))
			condition = true;
		assertTrue(condition);
	}
	
	@Test
	public void testGetVideosUser() throws Exception{
		boolean condition = true;
		List<Video> videos = videoService.getVideosUser(1);
		List<Video> allVideos = videoService.getVideos();
		for(Video v: videos){
			boolean cond = false;
			for(Video v1: allVideos){
				if(compareVideos(v, v1)){
					cond = true;
					break;
				}
			}
			condition = condition && cond;
		}
		assertTrue(condition);
	}

	@Test
	public void testGetRequestsUser() throws Exception{
		boolean condition = true;
		List<Video> videos = videoService.getVideosUser(1);
		List<Video> allVideos = videoService.getVideos();
		for(Video v: videos){
			boolean cond = false;
			for(Video v1: allVideos){
				if(compareVideos(v, v1)){
					cond = true;
					break;
				}
			}
			condition = condition && cond;
		}
		assertTrue(condition);
	}

	@Test
	public void testValidGetVideoById() throws Exception{
		boolean condition = false;
		Video v = videoService.getVideoById(1);
		if (v.getVideoId() == 1)
			condition = true;
		assertTrue(condition);
	}

	@Test
	public void testInvalidGetVideoById() throws Exception{
		boolean condition = false;
		if (videoService.getVideoById(0) == null)
			condition = true;
		assertTrue(condition);
	}
	
	@Test
	public void testValidAddVideo() throws Exception{
		boolean condition = false;
		Video video = new Video();
		video.setMovieId(2);
		video.setOwnerId(1);
		Video response = videoService.addVideo(video);
		List<Video> videos = videoService.getVideos();
		for (Video v : videos) {
			if (v.getVideoId().equals(response.getVideoId())){
				condition = true;
				break;
			}
		}
		assertTrue(condition);
	}
	
	@Test
	public void testInvalidAddVideo() throws Exception{
		boolean condition = false;
		Video video = new Video();
		try{
			videoService.addVideo(video);
		}
		catch(Exception e){
			condition = true;
		}
		assertTrue(condition);
	}
}