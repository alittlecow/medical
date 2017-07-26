package com.jubo;

import cn.beecloud.BeeCloud;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


@SpringBootApplication
public class Application extends SpringBootServletInitializer {
	private static final String BEECLOUD_APP_ID = "ce7e9291-1d25-4941-b926-367604b4ff8c";
	private static final String BEECLOUD_TEST_SECRET = "89d77472-ecbd-488b-8c91-1a1b0129158c";

	private static void initBeeCloud(){
		// TODO: 2017/7/13  暂时没有开通对应的支付渠道,无法获取appSecret 和 MasterSecret
		// 仅能用于测试环境
		BeeCloud.registerApp(BEECLOUD_APP_ID,BEECLOUD_TEST_SECRET, null,null);
		BeeCloud.setSandbox(true);

	}

	public static void main(String[] args) {
		initBeeCloud();
		SpringApplication.run(Application.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

}
