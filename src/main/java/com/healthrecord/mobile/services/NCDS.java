package com.healthrecord.mobile.services;

public enum NCDS implements IService {

	DYSLIPIDEMIA("/services/ncds/dysipidemia.json");

	private String url;

	NCDS(String url) {
		this.url = url;
	}

	public String url() {
		return url;
	}


}
