package com.healthrecord.mobile.model;

import java.io.Serializable;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserProfileM implements Serializable {

	private String id;
	private UserIndicatorM[] indicators;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public UserIndicatorM[] getIndicators() {
		return indicators;
	}
	public void setIndicators(UserIndicatorM[] indicators) {
		this.indicators = indicators;
	}
	@Override
	public String toString() {
		return "UserProfileM [id=" + id + ", indicators=" + Arrays.toString(indicators) + "]";
	}
	
	
}
