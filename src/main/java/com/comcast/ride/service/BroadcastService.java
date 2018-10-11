package com.comcast.ride.service;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.comcast.ride.entity.Ride;
import com.comcast.ride.entity.User;
import com.comcast.ride.model.BroadcastJsonResponse;
import com.comcast.ride.model.BroadcastJsonResponse.Data;

@Service
public class BroadcastService {
	
	private String url = "https://fcm.googleapis.com/fcm/send";
	private String key ="key=AIzaSyBGOfWTWc_N3Smqtoc2RAgVrIzVirzbo18";
	
	
	
	
	public void broadcastToUsers(List<User> userList,Ride ride) {
		
		
		for(User user:userList) {
			String token = user.getBroadcasterToken();
			
			RestTemplate restTemplate = new RestTemplate();
			
			restTemplate.exchange(this.url, HttpMethod.POST, createHttpEntity(token,ride),
					Object.class);
		}
		
	}
	
	
	private HttpEntity<Object> createHttpEntity(String token,Ride ride) {
		BroadcastJsonResponse request = new BroadcastJsonResponse();
		
		request.setTo(token);
		request.setContent_available(true);
		
		Data data = request.new Data();
		data.setTitle("Ride");
		data.setBody("rideId:"+ride.get_id()+"|lenderId:"+ride.getLenderId());
		
		request.setData(data);
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", key);
		HttpEntity<Object> entity = new HttpEntity<>(request, headers);
		return entity;
	}
	
	
	

}
