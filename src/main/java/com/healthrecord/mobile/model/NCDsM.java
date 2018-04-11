package com.healthrecord.mobile.model;

import java.io.Serializable;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
public class NCDsM implements Serializable {
	
	private String _id;
	private String name_th;
	private String name_en;
	private String[] indicators;
//	public String getId() {
//		return id;
//	}
//	public void setId(String id) {
//		this.id = id;
//	}
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
	public String[] getIndicators() {
		return indicators;
	}
	public void setIndicators(String[] indicators) {
		this.indicators = indicators;
	}
	@Override
	public String toString() {
		return "DyslipidemiaM [id=" + _id + ", name_th=" + name_th + ", name_en=" + name_en + "]";
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
 
}
