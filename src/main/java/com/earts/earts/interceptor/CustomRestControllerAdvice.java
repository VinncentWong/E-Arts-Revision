package com.earts.earts.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.earts.earts.entity.Response;
import com.earts.earts.exception.ArtistNotFoundException;
import com.earts.earts.exception.NotAuthenticatedException;
import com.earts.earts.util.ResponseUtil;

@RestControllerAdvice
public class CustomRestControllerAdvice {
	
	private final ResponseUtil util;
	
	@Autowired
	CustomRestControllerAdvice(ResponseUtil util){
		this.util = util;
	}
	
	@ExceptionHandler(ArtistNotFoundException.class)
	public ResponseEntity<Response> sendArtistNotFoundException(){
		return util.sendInternalServerError("data artist tidak ditemukan di database", false);
	}
	
	@ExceptionHandler(NotAuthenticatedException.class)
	public ResponseEntity<Response> sendInternalServerErrorException(){
		return util.sendInternalServerError("user tidak terautentikasi!", false);
	}
}
