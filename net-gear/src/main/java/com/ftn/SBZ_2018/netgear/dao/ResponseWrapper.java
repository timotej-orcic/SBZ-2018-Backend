package com.ftn.SBZ_2018.netgear.dao;

import java.io.Serializable;

public class ResponseWrapper<T> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private T payload;
	
	private String message;
	
	private boolean success;
	
	public ResponseWrapper() {}

	public ResponseWrapper(T payload, String message, boolean success) {
		this.payload = payload;
		this.message = message;
		this.success = success;
	}

	public T getPayload() {
		return payload;
	}

	public void setPayload(T payload) {
		this.payload = payload;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
