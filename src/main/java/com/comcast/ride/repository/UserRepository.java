package com.comcast.ride.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.comcast.ride.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	
	public User findByUserId(String userId);

}
