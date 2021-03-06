package com.cuize.task.service.dto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GlobalConfig {
	@Value("${app.name:task}")
	private String appName;

	@Value("${env}")
	private String env;

	@Value("${timestamp}")
	private String buildTimestamp;

	@Value("${batch.succ.mobiles}")
	private String getBatchSuccMobiles;

	@Value("${batch.fail.mobiles}")
	private String getBatchFailMobiles;

	@Value("${ds.env}")
	private String dsEnv;
	
	@Value("${hq.otaaccount}")
	private String hqAccount;

	@Value("${hq.otapassword}")
	private String hqPassword;
	
	@Value("${hq.url.getOrder}")
	private String hqGetOrderUrl;
	
	public String getAppName() {
		return appName;
	}

	public String getEnv() {
		return env;
	}

	public String getBuildTimestamp() {
		return buildTimestamp;
	}

	public String getGetBatchSuccMobiles() {
		return getBatchSuccMobiles;
	}

	public String getGetBatchFailMobiles() {
		return getBatchFailMobiles;
	}

	public String getDsEnv() {
		return dsEnv;
	}

	public String getHqGetOrderUrl() {
		return hqGetOrderUrl;
	}

	public String getHqAccount() {
		return hqAccount;
	}

	public String getHqPassword() {
		return hqPassword;
	}
}
