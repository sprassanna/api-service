package com.comcast.ride.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.comcast.ride.entity.User;
import com.comcast.ride.repository.RideRepository;
import com.comcast.ride.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	RideRepository rideRepo;

	public List<User> getMatchingUsersList(String userId) {
		User user = userRepo.findByUserId(userId);

		Query query = new Query();

		Criteria criteria1 = new Criteria();

		criteria1.orOperator(Criteria.where("destination").is(user.getDestination()),
				Criteria.where("viaLocations").is(user.getViaLocations()));

		query.addCriteria(criteria1);

		List<User> usersList = mongoTemplate.find(query, User.class);

		List<User> validUsersList = new ArrayList<>();

		for (User matchedUser : usersList) {
			if (matchedUser.getUserId().equalsIgnoreCase(userId)) {
				// ignore
			} else {
				validUsersList.add(matchedUser);
			}
		}

		return validUsersList;
	}
}
