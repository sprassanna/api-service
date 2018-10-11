package com.comcast.ride.config;

import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import com.mongodb.MongoClient;

 
public class DBConfiguration extends AbstractMongoConfiguration {
	

	

	@Override
	public MongoClient mongoClient() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getDatabaseName() {
		// TODO Auto-generated method stub
		return null;
	}
}
