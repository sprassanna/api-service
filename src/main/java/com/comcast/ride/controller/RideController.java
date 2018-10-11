package com.comcast.ride.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.comcast.ride.entity.Ride;
import com.comcast.ride.entity.User;
import com.comcast.ride.repository.RideRepository;
import com.comcast.ride.service.BroadcastService;
import com.comcast.ride.service.RideService;
import com.comcast.ride.service.UserService;

@RestController
@RequestMapping(value = "/ride")
public class RideController {

	@Autowired
	RideRepository rideRepo;

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
	RideService rideService;
	
	@Autowired
	
	UserService userService;
	
	@Autowired
	BroadcastService broadcastService;

	@RequestMapping(value = "/lender/{lenderId}", method = RequestMethod.GET)
	public @ResponseBody Ride getRide(@PathVariable("lenderId") String lenderId) {
		return rideRepo.findByLenderId(lenderId);

	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Ride> getRideById(@PathVariable("id") String id) {
		return rideRepo.findById(id);

	}

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<Ride> getAllRides() {
		return rideRepo.findAll();

	}

	@RequestMapping(value = "", method = RequestMethod.PUT, produces = "application/json")
	public void createRide(@RequestBody Ride ride) {
		
		rideService.createRide(ride);
		
		List<User> userListTobeBeNotified = userService.getMatchingUsersList(ride.getLenderId());
		
		
		broadcastService.broadcastToUsers(userListTobeBeNotified,ride);
		
		
	}

	@RequestMapping(value = "/{rideId}/{accepterId}", method = RequestMethod.POST, produces = "application/json")
	public void updateRide(@PathVariable("rideId") String rideId,@PathVariable("accepterId") String accepterId) throws Exception {
		
		Query query = new Query();
		
		 Criteria criteria1 = Criteria.where("_id").is(rideId);
		 
		  
		query.addCriteria(criteria1);

		Ride currentRide = mongoTemplate.findOne(query, Ride.class);
		
		if (currentRide == null) {
			throw new Exception("Ride Details Not found ");
		}
		else if(currentRide.getAcceptedCount() == currentRide.getRideCount()) {
			Update update = new Update();
			update.set("active", true);
			
			currentRide.setActive(false);
			throw new Exception("no slot available");
		}
		else {
			 
				Update update = new Update();
				List<String> acceptorList = currentRide.getAccepterId();
				acceptorList.add(accepterId);
				update.set("accepterId", acceptorList);
				update.set("acceptedCount", currentRide.getAcceptedCount() + 1);
				mongoTemplate.updateFirst(query, update, Ride.class);

		}
	}

}
