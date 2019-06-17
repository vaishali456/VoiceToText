package com.tatacommunications.vtt.entity;

public class RestResponse {
	Object data;
	int responseCode;
	String responseMessage;
	
	public RestResponse(Object data,int responseCode,String responseMessage){
		this.data=data;
		this.responseCode=responseCode;
		this.responseMessage=responseMessage;
	}
	

}
