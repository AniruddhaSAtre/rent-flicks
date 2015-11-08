package com.rentflicks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentflicks.model.Request;

public interface RequestRepository extends JpaRepository<Request, Integer> {


}
