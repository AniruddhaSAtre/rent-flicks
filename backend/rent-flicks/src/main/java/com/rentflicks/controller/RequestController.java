package com.rentflicks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rentflicks.markers.Create;
import com.rentflicks.model.Request;
import com.rentflicks.model.User;
import com.rentflicks.service.RequestService;

@RestController
public class RequestController {
	
	RequestService requestService;

	@Autowired
	public RequestController(RequestService requestService) {
		super();
		this.requestService = requestService;
	}

	@RequestMapping(value = "/requests", method = RequestMethod.GET)
	public List<Request> GetRequests() {
		return requestService.getRequests();
	}
	
	@RequestMapping(value = "/borrow", method = RequestMethod.POST)
	public Request sendBorrowRequest(@RequestBody Request request) {
		return requestService.createRequest(request);
	}
	
	@RequestMapping(value = "/accept", method = RequestMethod.POST)
	public Request acceptBorrowRequest(@RequestBody Request request) {
		Request req = requestService.findOne(request.getRequestId());
		req.setCheckOutDate(request.getCheckOutDate());
		return requestService.createRequest(req);
	}
	
	@RequestMapping(value = "/deny", method = RequestMethod.POST)
	public void denyBorrowRequest(@RequestBody Request request) {
		requestService.deleteRequest(request.getRequestId());
		return ;
	}
	/*
	 * Get Borrow and Return Requests for User id =1
	 * SELECT video.video_id, movie.title, movie.movie_id, video.owner_id, 
	 * request.borrower_id FROM user, video, request, movie 
	 * WHERE user.user_id = video.owner_id AND video.video_id = request.video_id 
	 * AND video.movie_id = movie.movie_id and user.user_id = 1
	 * */
}
