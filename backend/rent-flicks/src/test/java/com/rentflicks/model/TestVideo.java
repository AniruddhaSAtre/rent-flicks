package com.rentflicks.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rentflicks.RentFlicksApplication;
import com.rentflicks.service.VideoService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RentFlicksApplication.class)
public class TestVideo {
	
	private Video video;
	
	@Autowired
	private VideoService videoService;
	
	@Before
	public void setUp() throws Exception{
		video = new Video();
		video.setMovieId(2);
		video.setOwnerId(1);
	}
	
	@Test
	public void testValidMovieId() throws Exception{
		boolean exceptionCaught = false;
		try{
			videoService.addVideo(video);
		}
		catch(Exception e){
			exceptionCaught = true;
		}
		assertFalse(exceptionCaught);
	}
	
	@Test
	public void testInvalidMovieId() throws Exception{
		boolean exceptionCaught = false;
		try{
			video.setMovieId(1);
			videoService.addVideo(video);
		}
		catch(Exception e){
			exceptionCaught = true;
		}
		assertTrue(exceptionCaught);
	}
	
	@Test
	public void testValidOwnerId() throws Exception{
		boolean exceptionCaught = false;
		try{
			videoService.addVideo(video);
		}
		catch(Exception e){
			exceptionCaught = true;
		}
		assertFalse(exceptionCaught);
	}
	
	@Test
	public void testInvalidOwnerId() throws Exception{
		boolean exceptionCaught = false;
		try{
			video.setOwnerId(0);
			videoService.addVideo(video);
		}
		catch(Exception e){
			exceptionCaught = true;
		}
		assertTrue(exceptionCaught);
	}
	
	
}

