package com.comcast.ride.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class User {
	
	@Id
	private String _id;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	private String userId;

	private String phoneNumber;

	private String viaLocations;

	private String destination;
	
	private String broadcasterToken;
	

	public String getBroadcasterToken() {
		return broadcasterToken;
	}

	public void setBroadcasterToken(String broadcasterToken) {
		this.broadcasterToken = broadcasterToken;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	 

	public String getViaLocations() {
		return viaLocations;
	}

	public void setViaLocations(String viaLocations) {
		this.viaLocations = viaLocations;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", phoneNumber=" + phoneNumber + ", viaLocations=" + viaLocations
				+ ", destination=" + destination + "]";
	}

}
