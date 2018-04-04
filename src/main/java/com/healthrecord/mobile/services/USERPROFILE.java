package com.healthrecord.mobile.services;

public enum USERPROFILE implements IService {
	
	U0001("/services/profiles/u0001.json");

	private String url;

	USERPROFILE(String url) {
		this.url = url;
	}

	public String url() {
		return url;
	}

}
