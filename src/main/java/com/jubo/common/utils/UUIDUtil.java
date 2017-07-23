package com.jubo.common.utils;

import java.util.UUID;

public class UUIDUtil {
	public static String getUUId() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

}
