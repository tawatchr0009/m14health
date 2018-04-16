package com.healthrecord.mobile.services.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Iterator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonNodeDataServiceImpl {

	private String db_endpoint;
	
	private String charset = "UTF-8";
	
	public JsonNodeDataServiceImpl(String endpoint) {
		super();
		this.db_endpoint = endpoint;
	}
	
	public JsonNode getResultSetJsonNode(String query_endpoint) throws Exception {
		System.out.println("query endpoint:"+query_endpoint);
		InputStream is  = new URL(query_endpoint).openStream();
		BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName(charset)));
		 ObjectMapper mapper = new ObjectMapper();
		 return mapper.readTree(rd);
	}
	public JsonNode getDataJsonNode(String id) throws Exception {
		String endpoint = db_endpoint +"/_all_docs?include_docs=true&keys=[\""+id+"\"]";
		JsonNode rsNode = getResultSetJsonNode(endpoint);
		JsonNode rowsNode = rsNode.path("rows");
		Iterator<JsonNode> iter = rowsNode.elements();
		if(iter.hasNext()) {
			JsonNode rowNode = iter.next();
			return rowNode.path("doc");
		}
		return null;
	}
}
