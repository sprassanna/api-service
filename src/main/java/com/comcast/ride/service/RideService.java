package com.comcast.ride.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comcast.ride.entity.Ride;
import com.comcast.ride.repository.RideRepository;

@Service
public class RideService {
	
	@Autowired
	RideRepository  rideRepo;

	public void createRide(Ride ride) {
		
		ride.setDate(new Date().toString());
		ride.setActive(true);
		rideRepo.save(ride);
		
	}

}
