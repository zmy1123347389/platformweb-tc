package com.behere.system.util;

import java.util.UUID;

public class UUIDUtil {
	public static String randomUUID() {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		return uuid;
	}
}