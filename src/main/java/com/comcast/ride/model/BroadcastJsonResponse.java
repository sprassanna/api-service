package com.comcast.ride.model;

import org.springframework.stereotype.Component;

@Component
public class BroadcastJsonResponse {

	private String to;

	private boolean content_available;

	private Data data;

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public boolean getContent_available() {
		return content_available;
	}

	public void setContent_available(boolean content_available) {
		this.content_available = content_available;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}
	
	

	@Override
	public String toString() {
		return "BroadcastJsonResponse [to=" + to + ", content_available=" + content_available + ", data=" + data + "]";
	}



	public class Data {

		private String title;

		private String body;

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getBody() {
			return body;
		}

		public void setBody(String body) {
			this.body = body;
		}

		@Override
		public String toString() {
			return "Data [title=" + title + ", body=" + body + "]";
		}
		
		

	}

}
