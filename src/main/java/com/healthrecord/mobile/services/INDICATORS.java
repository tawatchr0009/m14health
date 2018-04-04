package com.healthrecord.mobile.services;

public enum INDICATORS implements IService{

	TRIGLYCERIDE("/services/indicators/trigecerly.json"),
	CHOLESTERAL("/services/indicators/cholesteral.json");

	private String url;

	INDICATORS(String url) {
		this.url = url;
	}

	public String url() {
		return url;
	}

}
