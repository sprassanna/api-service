package com.comcast.ride.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.comcast.ride.entity.Ride;

@Repository
public interface RideRepository extends MongoRepository<Ride, String> {

	public Ride findByLenderId(String lenderId);

}
