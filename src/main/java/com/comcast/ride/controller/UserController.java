package com.comcast.ride.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.comcast.ride.entity.Ride;
import com.comcast.ride.entity.User;
import com.comcast.ride.repository.RideRepository;
import com.comcast.ride.repository.UserRepository;
import com.comcast.ride.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	UserRepository userRepo;

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
	RideRepository rideRepo;
	
	@Autowired
	UserService userService;

	@RequestMapping(value = "", method = RequestMethod.PUT, produces = "application/json")
	public void saveUser(@RequestBody User user) {
		userRepo.save(user);

	}

	@RequestMapping(value = "{userId}", method = RequestMethod.GET)
	public @ResponseBody User getUser(@PathVariable("userId") String userId) {
		return userRepo.findByUserId(userId);

	}
	
	@RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<User> getUserBasedonID(@PathVariable("id") String id) {
		return userRepo.findById(id);

	}

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<User> getUsersList() {
		return userRepo.findAll();

	}

	@RequestMapping(value = "/match/{userId}", method = RequestMethod.GET)
	public @ResponseBody List<User> getMatchingUsersList(@PathVariable("userId") String userId) {

		return userService.getMatchingUsersList(userId);
	}
	
	@RequestMapping(value = "/findMyRider/{userId}", method = RequestMethod.GET)
	public @ResponseBody User   getMyLender(@PathVariable("userId") String userId) {
		
		List<Ride> ridesList = rideRepo.findAll();
		
		Ride matchingRide =null;
		
		for(Ride ride : ridesList) {
			
			if(ride.getAccepterId().contains(userId)) {
				matchingRide = ride;
				break;
			}
			
		}
		
		User user = userRepo.findByUserId(matchingRide.getLenderId());
		
		return user;
		
	}
	

}
