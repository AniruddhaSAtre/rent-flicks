package com.rentflicks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentflicks.model.Request;
import com.rentflicks.repository.RequestRepository;

import java.util.List;

@Service
public class RequestService {

	RequestRepository requestRepository;

	@Autowired
	public RequestService(RequestRepository requestRepository) {
		super();
		this.requestRepository = requestRepository;
	}

	public List<Request> getRequests() {
		List<Request> requests = requestRepository.findAll();
		return requests;
	}
	
	public Request createRequest(Request request){
		Request req = requestRepository.save(request);
		return req;
	}
	
	public Request findOne(Integer id){
		Request req = requestRepository.findOne(id);
		return req;
	}
	
	public void deleteRequest(Integer id){
		requestRepository.delete(id);
	}

}
