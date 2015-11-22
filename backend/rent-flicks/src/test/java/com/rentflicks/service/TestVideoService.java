package com.rentflicks.service;

import java.util.List;

import org.junit.Assert;
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

	@Test
	public void testVideos() throws Exception {
		List<Video> videos = videoService.getVideos();
		Assert.assertTrue(videos.size() != 0);
	}

	public void testVideosUser() throws Exception {
		List<Video> videosUser = videoService.getVideosUser(1);
		Assert.assertTrue(videosUser.size() != 0);
	}

	public void testRequestsUser() throws Exception {
		List<Video> requestsUser = videoService.getRequestsUser(20);
		Assert.assertTrue(requestsUser.isEmpty());

	}

}