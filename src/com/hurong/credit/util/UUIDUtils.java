package com.hurong.credit.util;

import java.util.UUID;

import org.junit.Test;

public class UUIDUtils {
	
	public static String getUUID(){
		UUID randomUUID = UUID.randomUUID();
		return randomUUID.toString().replace("-", "");
	}
}
