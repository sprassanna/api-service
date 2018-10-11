package com.comcast.ride.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ride")
public class Ride {

	@Id
	private String _id;
	private String lenderId;
	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	private List<String> accepterId = new ArrayList<>();

	private int rideCount;
	private int acceptedCount;
	
	private boolean active= false;
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	private String date;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getLenderId() {
		return lenderId;
	}

	public void setLenderId(String lenderId) {
		this.lenderId = lenderId;
	}

	public List<String> getAccepterId() {
		return accepterId;
	}

	public void setAccepterId(List<String> accepterId) {
		this.accepterId = accepterId;
	}

	public int getRideCount() {
		return rideCount;
	}

	public void setRideCount(int rideCount) {
		this.rideCount = rideCount;
	}

	public int getAcceptedCount() {
		return acceptedCount;
	}

	public void setAcceptedCount(int acceptedCount) {
		this.acceptedCount = acceptedCount;
	}

	@Override
	public String toString() {
		return "Ride [lenderId=" + lenderId + ", accepterId=" + accepterId + ", rideCount=" + rideCount
				+ ", acceptedCount=" + acceptedCount + "]";
	}

}
