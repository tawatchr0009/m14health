package com.healthrecord.mobile.model;

import java.util.Arrays;

@SuppressWarnings("serial")
public class UserIndicatorM extends IndicatorID {
	
	private String value;
	private String date;
	private UserIndicatorHistoryM[] historys;
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
	public UserIndicatorHistoryM[] getHistorys() {
		return historys;
	}
	public void setHistorys(UserIndicatorHistoryM[] historys) {
		this.historys = historys;
	}
	@Override
	public String toString() {
		return "UserIndicatorM [value=" + value + ", date=" + date + ", historys=" + Arrays.toString(historys) + ", id="
				+ id + "]";
	}
	
	

}
