package com.earts.earts.entity;

import lombok.Data;

@Data
public class Response {
    private String message;
	
	private boolean success;
	
	private Object data;
}
