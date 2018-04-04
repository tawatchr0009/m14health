package com.healthrecord.mobile.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UserIndicatorHistoryM implements Serializable {
	
//	private String indicatorID;
	private String value;
	private String date;
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "UserIndicatorHistoryM [value=" + value + ", date=" + date + "]";
	}

}
