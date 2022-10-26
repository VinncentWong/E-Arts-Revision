package com.earts.earts.util;

@FunctionalInterface
public interface IJwt <T> {
	String generateToken(T obj);
}
