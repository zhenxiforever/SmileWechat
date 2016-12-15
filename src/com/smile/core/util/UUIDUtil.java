package com.smile.core.util;

import java.util.UUID;

public class UUIDUtil {
	
	public static String getUUID(){
		UUID uuid=UUID.randomUUID();
		String id=uuid.toString().replace("-", "").toUpperCase();
		return id;
	}
}
