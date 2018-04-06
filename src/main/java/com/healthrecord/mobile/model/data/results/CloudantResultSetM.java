package com.healthrecord.mobile.model.data.results;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CloudantResultSetM<T extends Serializable> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int totalRows;
	private List<CloudantResultM<T>> rows = new ArrayList<CloudantResultM<T>>();
	
	@JsonProperty("total_rows")
	public int getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
	public List<CloudantResultM<T>> getRows() {
		return rows;
	}
	public void setRows(List<CloudantResultM<T>> rows) {
		this.rows = rows;
	}
	
	

}
