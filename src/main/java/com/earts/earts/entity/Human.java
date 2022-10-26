package com.earts.earts.entity;

import java.util.Date;

public interface Human {
    Long getId();
	String getUsername();
	Date getCreatedAt();
	Role getRole();
}
