package com.earts.earts.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.earts.earts.entity.Response;

@Component
public class ResponseUtil {
    
    public ResponseEntity<Response> sendOk(String message, boolean success, Object data){
		Response response = new Response();
		response.setData(data);
		response.setMessage(message);
		response.setSuccess(success);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	public ResponseEntity<Response> sendCreated(String message, boolean success, Object data){
		Response response = new Response();
		response.setData(data);
		response.setMessage(message);
		response.setSuccess(success);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	public ResponseEntity<Response> sendForbidden(String message, boolean success){
		Response response = new Response();
		response.setData(null);
		response.setMessage(message);
		response.setSuccess(success);
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
	}
	
	public ResponseEntity<Response> sendUnauthorized(String message, boolean success){
		Response response = new Response();
		response.setData(null);
		response.setMessage(message);
		response.setSuccess(success);
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
	}
	
	public ResponseEntity<Response> sendInternalServerError(String message, boolean success){
		Response response = new Response();
		response.setData(null);
		response.setMessage(message);
		response.setSuccess(success);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}
	
	public ResponseEntity<Response> sendBadRequest(String message, boolean success){
		Response response = new Response();
		response.setData(null);
		response.setMessage(message);
		response.setSuccess(success);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
	
	public ResponseEntity<Response> sendAccepted(String message, boolean success){
		Response response = new Response();
		response.setData(null);
		response.setMessage(message);
		response.setSuccess(success);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
	}
}
