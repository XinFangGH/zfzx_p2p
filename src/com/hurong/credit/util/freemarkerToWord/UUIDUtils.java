package com.hurong.credit.util.freemarkerToWord;

import java.util.UUID;

public class UUIDUtils {
	
	/**
	 * 生成UUID
	 * @return
	 */
	public static String getUUID(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}
	
}
