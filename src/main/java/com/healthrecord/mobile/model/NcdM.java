package com.healthrecord.mobile.model;

import java.io.Serializable;
import java.util.Arrays;

@SuppressWarnings("serial")
public class NcdM implements Serializable {
	
	private String id;
	private String name_th;
	private String name_en;
	private IndicatorID[] indicators;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName_th() {
		return name_th;
	}
	public void setName_th(String name_th) {
		this.name_th = name_th;
	}
	public String getName_en() {
		return name_en;
	}
	public void setName_en(String name_en) {
		this.name_en = name_en;
	}
	public IndicatorID[] getIndicators() {
		return indicators;
	}
	public void setIndicators(IndicatorM[] indicators) {
		this.indicators = indicators;
	}
	@Override
	public String toString() {
		return "DyslipidemiaM [id=" + id + ", name_th=" + name_th + ", name_en=" + name_en + ", indicators="
				+ Arrays.toString(indicators) + "]";
	}
 
}
