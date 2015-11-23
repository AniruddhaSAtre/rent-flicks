package com.rentflicks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentflicks.model.Video;

public interface VideoRepository extends JpaRepository<Video, Integer> {

	public List<Video> findByOwnerId(Integer ownerId);
	public List<Video> findByRequestsBorrowerUserId(Integer borrowerId);
}