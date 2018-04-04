package com.healthrecord.mobile.model;

@SuppressWarnings("serial")
public class IndicatorM extends IndicatorID {
	
	private String name;
	private String unit;
	private String type;
	private String value;
	@Override
	public String toString() {
		return "IndicatorM [name=" + name + ", unit=" + unit + ", type=" + type + ", value=" + value + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
