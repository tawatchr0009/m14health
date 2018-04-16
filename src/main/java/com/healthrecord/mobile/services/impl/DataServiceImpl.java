package com.healthrecord.mobile.services.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthrecord.mobile.model.UserIndicatorM;
import com.healthrecord.mobile.model.UserProfileM;
import com.healthrecord.mobile.model.data.results.CloudantResultM;
import com.healthrecord.mobile.model.data.results.CloudantResultSetM;
import com.healthrecord.mobile.model.data.results.ProfileResultSetM;


public abstract class DataServiceImpl<T extends Serializable,R extends CloudantResultSetM<T>> {
	protected abstract Class<T> getDataClass() throws Exception;
	protected abstract  Class<R> getDataResultSet() throws Exception;
	
	private String endpoint;
	
	private String charset = "UTF-8";
	
	public DataServiceImpl(String endpoint) {
		super();
		this.endpoint = endpoint;
	}
	
//	public class DataResultSetM extends CloudantResultSetM<T>{
//
//		/**
//		 * 
//		 */
//		private static final long serialVersionUID = 1L;
//		
//	}
	public JsonNode getJsonNode() throws Exception {
		InputStream is  = new URL(endpoint).openStream();
		BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName(charset)));
		 ObjectMapper mapper = new ObjectMapper();
		 return mapper.readTree(rd);
	}
	public CloudantResultSetM<T>  get() throws Exception {
		
		
		InputStream is  = new URL(endpoint).openStream();
		BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName(charset)));
	       
	     
	    ObjectMapper objMapper = new ObjectMapper();
//	    CloudantResultSetM rs =	objMapper.readValue(rd, this.getDataResultSet());
	    
//	   DataResultSetM rs = objMapper.readValue(rd, DataResultSetM.class);
	  R  rs =	objMapper.readValue(rd, this.getDataResultSet());
			
		
		return rs;
	}
	
//	protected abstract Class<CloudantResultSetM> getDataResultSet() ;


	public List<T> getAll() throws Exception {
		
		List<T> docs = new ArrayList<T>();
		
		CloudantResultSetM<T> rs = get();
		
		List<CloudantResultM<T>> items = rs.getRows();
		for(CloudantResultM<T> item: items) {
			T doc = item.getDoc();
				docs.add(doc);
			};
	
			
		
		return docs;
	}
	
	public void push(T t) throws Exception {
		
		
	}
	
}
