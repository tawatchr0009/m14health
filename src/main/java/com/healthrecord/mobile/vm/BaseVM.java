package com.healthrecord.mobile.vm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.zkoss.zk.ui.Executions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthrecord.mobile.services.IService;

public class BaseVM {

	protected <T> T callService (IService s, Class<T> c) {
		InputStream is = null;
		BufferedReader rd = null;
		try {
	        String endpoint_url = getContextRootURL();
	        endpoint_url += s.url();
	        
	        System.out.println("endpoint_url : "+endpoint_url);

	        is = new URL(endpoint_url).openStream();
	        rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	       
	        T item = getModel(rd, c);
			System.out.println("Print JSON "+item);
			
			return item;
		} catch (Exception e) {
			System.err.println("error.callService : " +e.getMessage());
		} finally {
			
			if (rd != null)
				try {
					rd.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			
			
			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			
			
		}
		return null;
	}
	
	protected JsonNode callJsonNodeService (IService s) {
		InputStream is = null;
		BufferedReader rd = null;
		try {
	        String endpoint_url = getContextRootURL();
	        endpoint_url += s.url();
	        
	        System.out.println("endpoint_url : "+endpoint_url);

	        is = new URL(endpoint_url).openStream();
	        rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	       
	        ObjectMapper objMapper = new ObjectMapper();
			JsonNode docNode =	objMapper.readTree(rd);
			System.out.println("Print JSON id "+docNode.path("id").asText());
//			
//			return item;
			return docNode;
		} catch (Exception e) {
			System.err.println("error.callService : " +e.getMessage());
		} finally {
			
			if (rd != null)
				try {
					rd.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			
			
			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			
			
		}
		return null;
	}
	
	public String getContextRootURL() throws Exception {
		String port = ( Executions.getCurrent().getServerPort() == 80 ) ? "" : (":" + Executions.getCurrent().getServerPort());
        String endpoint_url = Executions.getCurrent().getScheme() + "://" + Executions.getCurrent().getServerName() + port + Executions.getCurrent().getContextPath();
        return endpoint_url;
	}
	
	public <T> T getModel(Reader r, Class<T> c) throws Exception {
		ObjectMapper objMapper = new ObjectMapper();
		T model =	objMapper.readValue(r, c);
		return model;
	}
}
