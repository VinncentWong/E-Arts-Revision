package com.earts.earts.app;

import java.util.Date;

public interface Human {
    Long getId();
	String getUsername();
	Date getCreatedAt();
	Role getRole();
}
